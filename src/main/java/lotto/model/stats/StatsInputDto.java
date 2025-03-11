package lotto.model.stats;

import java.util.List;
import lotto.model.prize.IPrizeOutput;

public record StatsInputDto(List<IPrizeOutput> purchasedLotto) implements IStatsInput {

    public static StatsInputDto of(List<IPrizeOutput> prizes) {
        return new StatsInputDto(prizes);
    }
}
