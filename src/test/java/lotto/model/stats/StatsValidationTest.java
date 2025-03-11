package lotto.model.stats;

import java.util.List;
import lotto.model.prize.IPrizeOutput;
import lotto.model.prize.PrizeEnum;
import lotto.model.prize.PrizeOutputDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatsValidationTest {

    @DisplayName("calculate profit rate")
    @Test
    void calculateProfitRate() {
        // given
        List<IPrizeOutput> prizes = List.of(
                PrizeOutputDto.of(PrizeEnum.NONE_0),
                PrizeOutputDto.of(PrizeEnum.NONE_1),
                PrizeOutputDto.of(PrizeEnum.NONE_0),
                PrizeOutputDto.of(PrizeEnum.NONE_2),
                PrizeOutputDto.of(PrizeEnum.NONE_0),
                PrizeOutputDto.of(PrizeEnum.NONE_1),
                PrizeOutputDto.of(PrizeEnum.FIFTH),
                PrizeOutputDto.of(PrizeEnum.NONE_0)
        );
        double expected = calculateProfitRate(prizes);

        // when
        double actual = StatsOutputDto.of(StatsInputDto.of(prizes)).profitRate();

        // then
        Assertions.assertEquals(5000. / 8000. * 100., expected);
        Assertions.assertEquals(expected, actual);

    }

    private double calculateProfitRate(List<IPrizeOutput> prizes) {
        double profit = 0;
        double cost = 0;
        for (IPrizeOutput prize : prizes) {
            profit += prize.money();
            cost += 1000;
        }
        return profit / cost * 100;
    }
}
