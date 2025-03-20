import { fetchDataWithRetry } from "./fetchData";

const mistakePerChapter = {
  expression: 2, //14,
  project_structure: 0, // 11,
};

export const urlList = Object.entries(mistakePerChapter)
  .filter(([, mistakeNumber]) => mistakeNumber > 0)
  .flatMap(([chapterName, mistakeNumber]) => {
    return Array.from({ length: mistakeNumber }, (_, i) => {
      return `${chapterName}/${i + 1}`;
    });
  });
console.log(`urlList:`, urlList);

async function fetchEndpoint() {
  const result: Endpoint[] = [];
  for (const url of urlList) {
    const data = await fetchDataWithRetry(`api/${url}`);
    const chapterName = url.split("/")[0];
    const mistakeId = parseInt(url.split("/")[1]);
    const len = data.data.length || 0;

    const a = Array.from({ length: len }, (_, i) => {
      return {
        chapterName: chapterName,
        mistakeId: mistakeId,
        exampleId: i + 1,
      } as Endpoint;
    });

    result.push(...a);
  }
  return result;
}

interface Endpoint {
  chapterName: string;
  mistakeId: number;
  exampleId: number;
}

export const endpoints: Endpoint[] = await fetchEndpoint();
console.log(`endpoints:`, endpoints);
