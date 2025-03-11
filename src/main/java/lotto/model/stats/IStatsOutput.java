package lotto.model.stats;

import base.model.Schema;
import java.util.SortedMap;
import lotto.model.prize.IPrizeOutput;

public interface IStatsOutput extends Schema {
    SortedMap<IPrizeOutput, Integer> prizeStats();
}