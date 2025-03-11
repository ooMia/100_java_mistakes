package lotto.model.lotto;

import java.util.SortedSet;
import java.util.TreeSet;

public record LottoOutputDto(
        SortedSet<Integer> numbers
) implements ILottoOutput {
    public static ILottoOutput of(ILottoInput input) {
        return new LottoOutputDto(new TreeSet<>(input.numbers()));
    }

    public static LottoOutputDto of(TreeSet<Integer> numbers) {
        return new LottoOutputDto(numbers);
    }


    @Override
    public String toString() {
        return numbers.toString();
    }
}
