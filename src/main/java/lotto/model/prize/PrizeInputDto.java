package lotto.model.prize;

import java.util.SortedSet;
import lotto.model.lotto.winning.IWinningLottoOutput;
import lotto.model.lotto.winning.WinningLottoOutputDto;

public record PrizeInputDto(
        SortedSet<Integer> numbers,
        IWinningLottoOutput winningLotto,
        int bonusNumber
) implements IPrizeInput {
    public static PrizeInputDto of(
            SortedSet<Integer> numbers,
            SortedSet<Integer> winningLottoNumbers,
            int bonusNumber
    ) {
        var tmp = new PrizeInputDto(
                numbers,
                WinningLottoOutputDto.of(winningLottoNumbers),
                bonusNumber);
        new PrizeInputValidator().validate(tmp);
        return tmp;
    }
}
