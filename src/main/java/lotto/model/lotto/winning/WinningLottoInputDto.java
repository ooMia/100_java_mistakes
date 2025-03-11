package lotto.model.lotto.winning;

import java.util.List;

public record WinningLottoInputDto(
        List<Integer> numbers
) implements IWinningLottoInput {
}
