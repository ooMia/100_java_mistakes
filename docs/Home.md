### [base-패키지-구조](base-패키지-구조)
> 프로젝트 기반이 되는 소스가 담긴 base 패키지

### [lotto-패키지-구조](lotto-패키지-구조)
> 실제 문제를 해결하기 위한 구현체가 담긴 lotto 패키지

### [DTO-Convention](DTO-Convention)
> base.model과 lotto.model의 활용 사례

### [index](index)
> `github pages`로 배포하기 위한 랜딩 페이지 컨벤션
- 현재는 수동으로 wiki를 업로드하고 있다.
- 다음과 같은 순서로 배포를 자동화 할 수 있다.
   1. `actions/checkout`
   2. `git clone https://github.com/spring-templates/java-lotto.wiki.git` as `docs`
   3. `git add .` && `git push`
- 나머지 배포 workflow는 repo의 설정으로 자동화되어 있다.