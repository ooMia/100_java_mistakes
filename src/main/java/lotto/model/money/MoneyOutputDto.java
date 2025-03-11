package lotto.model.money;

public record MoneyOutputDto(
        int money
) implements IMoneyOutput, Comparable<IMoneyOutput> {
    public static MoneyOutputDto of(IMoneyInput input) throws IllegalArgumentException {
        return new MoneyOutputDto(input.money());
    }

    @Override
    public String toString() {
        return Integer.toString(money);
    }

    @Override
    public int compareTo(IMoneyOutput obj) {
        return Integer.compare(money, obj.money());
    }
}
