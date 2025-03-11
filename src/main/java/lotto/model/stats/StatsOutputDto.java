package lotto.model.stats;

import java.util.SortedMap;
import java.util.TreeMap;
import lotto.model.prize.IPrizeOutput;
import lotto.model.prize.PrizeEnum;
import lotto.model.prize.PrizeOutputDto;

public record StatsOutputDto(
        SortedMap<IPrizeOutput, Integer> prizeStats
) implements IStatsOutput {
    public static StatsOutputDto of(IStatsInput input) {
        var prizeStats = new TreeMap<IPrizeOutput, Integer>();
        for (var prize : PrizeEnum.values()) {
            prizeStats.put(PrizeOutputDto.of(prize), 0);
        }
        for (var prize : input.purchasedLotto()) {
            prizeStats.put(prize, prizeStats.get(prize) + 1);
        }
        return new StatsOutputDto(prizeStats);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (IPrizeOutput key : prizeStats.keySet()) {
            if (key.money() == 0) {
                continue;
            }
            sb.append(key);
            sb.append(" - %d개".formatted(prizeStats.get(key)));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    public double profitRate() {
        double profit = 0;
        double cost = 0;
        for (var entry : prizeStats.entrySet()) {
            profit += entry.getKey().money() * entry.getValue();
            cost += entry.getValue() * 1000L;
        }
        return profit / cost * 100;
    }
}