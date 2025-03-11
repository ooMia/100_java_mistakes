package lotto.model.lotto;

import java.util.List;
import lotto.model.lotto.winning.WinningLottoInputDto;

public record LottoInputDto(
        List<Integer> numbers
) implements ILottoInput {
    public static LottoInputDto of(List<Integer> numbers) {
        var tmp = new WinningLottoInputDto(numbers);
        new LottoInputValidator().validate(tmp);
        return new LottoInputDto(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
