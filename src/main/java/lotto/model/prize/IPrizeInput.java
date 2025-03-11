package lotto.model.prize;

import base.model.Schema;
import lotto.model.bonus.IBonusOutput;
import lotto.model.lotto.ILottoOutput;
import lotto.model.lotto.winning.IWinningLottoOutput;


public interface IPrizeInput extends Schema, ILottoOutput, IBonusOutput {
    IWinningLottoOutput winningLotto();
}
