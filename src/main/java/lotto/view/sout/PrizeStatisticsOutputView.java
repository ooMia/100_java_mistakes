package lotto.view.sout;

import base.view.View;
import lotto.model.stats.IStatsOutput;
import lotto.model.stats.StatsOutputDto;

public class PrizeStatisticsOutputView extends View<IStatsOutput> {

    public PrizeStatisticsOutputView() {
        header();
    }

    public void render(IStatsOutput dto) {
        var prizeStatsOutputDto = (StatsOutputDto) dto;
        System.out.println(prizeStatsOutputDto);
        printProfitRate(prizeStatsOutputDto);
    }

    @Override
    public void header() {
        super.header();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    private void printProfitRate(StatsOutputDto prizeStatsOutputDto) {
        System.out.println("총 수익률은 " + prizeStatsOutputDto.profitRate() + "%입니다.");
    }
}
