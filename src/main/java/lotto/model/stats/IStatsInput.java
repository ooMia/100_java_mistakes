package lotto.model.stats;

import base.model.Schema;
import java.util.List;
import lotto.model.prize.IPrizeOutput;

public interface IStatsInput extends Schema {
    List<IPrizeOutput> purchasedLotto();
}
