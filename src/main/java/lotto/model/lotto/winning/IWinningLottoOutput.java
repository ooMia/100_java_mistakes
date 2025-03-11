package lotto.model.lotto.winning;

import base.model.Schema;
import lotto.model.lotto.ILottoOutput;

public interface IWinningLottoOutput extends Schema, ILottoOutput {
    static IWinningLottoOutput of(IWinningLottoInput input) {
        return WinningLottoOutputDto.of(input);
    }
}
