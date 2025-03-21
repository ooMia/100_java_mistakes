1. [`Spring Initializr`](https://start.spring.io/#!type=gradle-project-kotlin&language=java&platformVersion=3.5.0-M2&packaging=jar&jvmVersion=23&groupId=com.example&artifactId=mistakes&name=mistakes&description=A%20practical%20project%20for%20implementing%20concepts%20from%20the%20book%20'100%20Java%20Mistakes%20and%20How%20to%20Avoid%20Them'&packageName=com.example.mistakes&dependencies=web,devtools,configuration-processor,lombok,restdocs)
   - `gradlew tasks` to see all available tasks
2. [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
   - [Setting formatter in VS Code: using `Google Java Format for VS Code`](https://github.com/google/google-java-format?tab=readme-ov-file#third-party-integrations)
   - [`ktlint --format`](https://github.com/pinterest/ktlint?tab=readme-ov-file#quick-start)


# 2. 표현식 Expression
1. [x] 숫자 연산자 우선순위 오해 OperationPriority
   - [x] 이진 시프트 BinaryShift
   - [x] 비트 연산자 BitwiseOperator
2. [x] 조건식의 괄호 누락 MissingParentheses
   - [x] &&, ||의 우선순위 LogicalOperatorPrecedence
   - [x] 조건 연산자와 덧셈 TernaryWithAddition
   - [x] 조건 연산자와 null 검사 TernaryWithNullCheck
3. [x] 덧셈이 아닌 결합으로 작동 StringConcatenation
4. [x] 멀티라인 문자열 리터럴 MultilineStringLiteral
5. [x] 단항 덧셈 연산자 UnaryPlusOperator
6. [ ] 조건 표현식의 묵시적 타입 변환 ImplicitTypeConversion
   - [ ] 조건 표현식의 박싱된 숫자 BoxedNumberConditional
   - [ ] 중첩 조건 표현식 NestedConditional
7. [ ] 비단락 논리 연산자 사용 NonShortCircuitOperator
8. [ ] &&와 || 혼용 MixedLogicalOperators
9. [ ] 잘못된 가변 인수 호출 VarArgsIssues
   - [ ] 모호한 가변 인수 호출 AmbiguousVarArgs
   - [ ] 배열과 컬렉션 혼용 ArrayCollectionMixup
   - [ ] 가변 인수에 원시 배열 전달 PrimitiveArrayToVarArgs 
10. [ ] 조건 연산자와 가변 인수 호출 TernaryWithVarArgs
11. [ ] 반환값 무시 IgnoredReturnValue
12. [ ] 새롭게 생성된 객체를 사용하지 않음 UnusedObjects
13. [ ] 잘못된 메서드를 참조하는 바인딩 IncorrectMethodBinding
14. [ ] 메서드 참조 시 잘못된 메서드 지정 WrongMethodReference