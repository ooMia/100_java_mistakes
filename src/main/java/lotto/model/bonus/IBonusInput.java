package lotto.model.bonus;

import base.model.Schema;
import lotto.model.lotto.winning.IWinningLottoOutput;

public interface IBonusInput extends Schema, IWinningLottoOutput {
    int bonusNumber();
}
