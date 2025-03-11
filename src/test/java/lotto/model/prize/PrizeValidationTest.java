package lotto.model.prize;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import lotto.model.bonus.BonusOutputDto;
import lotto.model.lotto.ILottoOutput;
import lotto.model.lotto.LottoOutputDto;
import lotto.model.lotto.winning.WinningLottoOutputDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PrizeValidationTest {

    private final List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
    private final WinningLottoOutputDto winningLotto = new WinningLottoOutputDto(new TreeSet<>(winningNumbers));
    private final BonusOutputDto bonus = new BonusOutputDto(7);

    @DisplayName("prize enum test")
    @Test
    void prizeEnumTest() {
        // given
        Map<PrizeEnum, Integer> prizeMap = createPrizeMap();

        // then
        prizeMap.forEach((prize, expected) -> Assertions.assertThat(expected).isEqualTo(prize.getPrize()));
    }

    private Map<PrizeEnum, Integer> createPrizeMap() {
        return Map.of(PrizeEnum.NONE_0, 0, PrizeEnum.FIFTH, 5_000, PrizeEnum.FOURTH, 50_000, PrizeEnum.THIRD, 1_500_000,
                PrizeEnum.SECOND, 30_000_000, PrizeEnum.FIRST, 2_000_000_000);
    }

    @DisplayName("checkPrizeByNumberMatched")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void checkPrizeByNumberMatched(int matched) {
        // given
        ILottoOutput lotto = createLotto(matched);
        Set<Integer> expectedPrizes = createExpectedPrizes(matched);

        // when
        Integer numberMatched = winningLotto.countNumberMatched(lotto);
        boolean bonusMatched = bonus.isBonusMatched(lotto);
        IPrizeOutput output = PrizeOutputDto.of(PrizeEnum.of(numberMatched, bonusMatched));
        Integer actualPrize = output.money();

        // then
        Assertions.assertThat(actualPrize).isIn(expectedPrizes);
    }

    private ILottoOutput createLotto(int matched) {
        List<Integer> matchedNumbers = matched == 0 ? List.of() : winningNumbers.subList(0, matched);
        List<Integer> unmatchedNumbers =
                matched == 6 ? List.of() : winningNumbers.subList(matched, 6).stream().map(n -> 45 - n).toList();
        SortedSet<Integer> combined = new TreeSet<>(matchedNumbers);
        combined.addAll(unmatchedNumbers);
        return new LottoOutputDto(combined);
    }

    private Set<Integer> createExpectedPrizes(int matched) {
        int prize = PrizeEnum.of(matched, false).getPrize();
        Set<Integer> expectedPrizes = new HashSet<>(List.of(prize));
        if (matched == 5) {
            expectedPrizes.add(PrizeEnum.of(matched, true).getPrize());
        }
        return expectedPrizes;
    }
}
