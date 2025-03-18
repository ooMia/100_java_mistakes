interface Response {
  message?: string;
  result?: {
    id: number;
    message: string;
    before: string;
    after: string;
    path: string;
  }[];
  length?: number;
}

export async function fetchWithRetry(
  url: string,
  retriesLeft: number,
  maxRetries: number = 3,
  delayMs: number = 10000
) {
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`HTTP Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    if (retriesLeft <= 0) throw error;

    console.log(`Retrying... (${maxRetries - retriesLeft + 1}/${maxRetries})`);
    await new Promise((resolve) => setTimeout(resolve, delayMs));
    return fetchWithRetry(url, retriesLeft - 1);
  }
}

export async function fetchDataWithRetry(
  apiPath: string,
  baseUrl: string = "http://localhost:8080",
  maxRetries: number = 3,
  delayMs: number = 10000
): Promise<{ data: Response; message: string }> {
  let data = { message: "No Data" };
  let message = "No Data";
  try {
    data = await fetchWithRetry(`${baseUrl}/${apiPath}`, maxRetries, delayMs);
    message = data.message || "Data Loaded Successfully";
  } catch (error) {
    if (error instanceof Error) {
      data = { message: error.message };
      message = error.message;
    }
  }
  return { data, message };
}
