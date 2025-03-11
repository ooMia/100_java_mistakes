package lotto.model.prize;

import base.model.Schema;
import lotto.model.money.IMoneyOutput;

public interface IPrizeOutput extends Schema, IMoneyOutput, Comparable<IPrizeOutput> {
    int matchedNumberCount();

    boolean isBonusMatched();
}
