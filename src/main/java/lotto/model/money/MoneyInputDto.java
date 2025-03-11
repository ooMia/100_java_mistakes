package lotto.model.money;

public record MoneyInputDto(
        int money
) implements IMoneyInput {
    public static MoneyInputDto of(Integer money) {
        var tmp = new MoneyInputDto(money);
        new MoneyInputValidator().validate(tmp);
        return tmp;
    }

    @Override
    public String toString() {
        return Integer.toString(money);
    }
}
