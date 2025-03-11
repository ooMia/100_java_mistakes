package lotto.model.stats;

import base.controller.Converter;
import base.view.View;
import java.util.SortedMap;
import java.util.TreeMap;
import lotto.model.prize.IPrizeOutput;
import lotto.model.prize.PrizeEnum;
import lotto.model.prize.PrizeOutputDto;

public class StatsConverter extends Converter<IStatsInput, IStatsOutput> {


    protected StatsConverter(
            View<IStatsOutput> outputView
    ) {
        super(outputView);
    }

    @Override
    public IStatsOutput convert(IStatsInput iStatsInput) {
        SortedMap<IPrizeOutput, Integer> res = new TreeMap<>();
        for (var key : PrizeEnum.values()) {
            res.put(PrizeOutputDto.of(key), 0);
        }
        for (var prize : iStatsInput.purchasedLotto()) {
            res.put(prize, res.get(prize) + 1);
        }
        return new StatsOutputDto(res);

    }

}
