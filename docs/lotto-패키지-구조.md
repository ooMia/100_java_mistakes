```text
src/main
└─java
    ├─base...
    │  
    └─lotto
        ├─controller
        ├─model
        │  ├─bonus
        │  ├─lotto
        │  │  └─winning
        │  ├─money
        │  ├─prize
        │  └─stats
        └─view
            ├─sin
            └─sout
```


# lotto.view

추상 클래스 `View`를 상속받는 클래스들은 필요에 따라 메서드를 `@Overrides`하여 자신의 기능을 구현한다.

![image](https://github.com/spring-templates/java-lotto/assets/96914905/20ff8bbc-2e1a-4a1c-bb45-960eca5d9e29)


# lotto.controller

![image](https://github.com/spring-templates/java-lotto/assets/96914905/c9ac4a32-2ae5-4441-a604-22c86b507812)

`ConsoleInput`은 내부적으로 다음과 같은 생성자를 갖는다.

따라서, 상속받는 클래스는 `super()` 메서드를 통해 본 명세를 만족하도록 호출해야만 한다.

```java
public abstract class ConsoleInput<IN extends Schema> implements AutoCloseable {
...
    protected ConsoleInput(View<IN> view, Validator<IN> validator, Scanner scanner) {
        this.scanner = scanner;
        this.view = view;
        this.validator = validator;
    }
...
}
```

## 구현

1. DI (Dependency Injection)

외부에서 적절한 인자를 넘겨주도록 구현할 수 있다.

이 경우, 다양성을 확보할 수 있지만, 호출부가 지저분해진다.

```java
private MoneyConsoleInput(View<IMoneyInput> view, Validator<IMoneyInput> validator, Scanner scanner) {
    super(view, validator, scanner);
}
```

2. IoC (Inversion of Control)

클래스 내부에 기본값을 설정해둘 수 있다.

이 경우, `생성자 오버로딩` 또는 `public static of()` 메서드를 제공하여 인스턴스를 생성한다.


```java
public class WinningLottoConsoleInput extends ConsoleInput<IWinningLottoInput> {
    private WinningLottoConsoleInput(
            View<IWinningLottoInput> view,
            Validator<IWinningLottoInput> validator,
            Scanner scanner
    ) {
        super(view, validator, scanner);
    }

    public static ConsoleInput<IWinningLottoInput> of(Scanner scanner) {
        return new WinningLottoConsoleInput(
                new WinningLottoInputView(),
                new WinningLottoInputValidator(),
                scanner
        );
    }
...
}
```

# lotto.model

- 참조: [DTO-Convention](DTO-Convention)

