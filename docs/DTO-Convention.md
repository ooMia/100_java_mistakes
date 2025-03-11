Java Record와 Interface를 활용하여 데이터를 구조화하고, 사용 관례를 표준화 할 수 있다.

1. 입출력에 사용되는 데이터의 형태를 interface로 정의한다.
2. Record가 해당 interface를 구현한다.

## 장점
> 타입과 변수명을 강제할 수 있다.

`ILottoOutput`을 구현하는 레코드는 반드시 동일한 타입과 변수명으로 선언되어야 한다.

```java
public interface ILottoOutput extends Schema {
    SortedSet<Integer> numbers();
}
```

레코드는 변수명을 토대로 getter 메서드를 생성함으로써 자동적으로 인터페이스를 구현한다.

```java
public record LottoOutputDto(
    SortedSet<Integer> numbers
) implements ILottoOutput {
    ...
}
```

## 단점
> 인터페이스 상속을 고려한 변수명 설정이 필요하다.

가령, `IPrizeInput`은 `ILottoOutput`을 상속받는다.

```java
public interface IPrizeInput extends Schema, ILottoOutput, IBonusOutput {
    IWinningLottoOutput winningLotto();
}
```
이 경우, `IPrizeInput`은 다음 getter를 가지게 된다.
- `SortedSet<Integer>` `numbers()`
- `IWinningLottoOutput` `winningLotto()`

`Prize`라는 맥락에서 `numbers`라는 변수명이 모호하다고 느낄 수 있다.

## 개선

위와 같은 모호함을 해결하기 위한 방법은 크게 두 가지이다.

1. 변수명 리팩토링
   - 서로 다른 맥락에서 공통적으로 구분되는 이름으로 변경한다.
   - 예를 들어, `numbers`를 `lottoNumbers`로 바꾼다면, `Prize` 맥락에서도 해당 속성이 `Lotto`에 대한 것임을 알 수 있다.
   - 하지만 `ILottoOutput`의 맥락에서는 lotto라는 명칭이 중복된다는 문제가 발생한다.
2. 상속 대신 참조
   - `extends ILottoOutput` 대신 다음과 같이 작성한다.

       ```java 
       public interface IPrizeInput extends Schema, IBonusOutput {
           ILottoOutput givenLotto();
           IWinningLottoOutput winningLotto();
       }
       ```

       이렇게 하면 `numbers()`를 호출하기 위해 한 단계의 메서드 체이닝을 거쳐야 하지만
       맥락은 이전보다 잘 전달되도록 작성할 수 있다.
