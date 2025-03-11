package lotto.model.prize;

import java.text.DecimalFormat;
import lotto.model.bonus.BonusOutputDto;
import lotto.model.lotto.ILottoOutput;
import lotto.model.lotto.winning.WinningLottoOutputDto;

public record PrizeOutputDto(
        int matchedNumberCount,
        boolean isBonusMatched,
        int money

) implements IPrizeOutput, Comparable<IPrizeOutput> {
    public static PrizeOutputDto of(PrizeEnum prizeEnum) {
        return new PrizeOutputDto(
                prizeEnum.getCountMatched(),
                prizeEnum.getIsBonusMatched(),
                prizeEnum.getPrize()
        );
    }

    public static PrizeOutputDto of(
            ILottoOutput lotto,
            WinningLottoOutputDto winningLotto,
            BonusOutputDto winningBonusNumber
    ) {
        return of(PrizeEnum.of(winningLotto.countNumberMatched(lotto), winningBonusNumber.isBonusMatched(lotto)));
    }

    @Override
    public String toString() {
        String matchCountMessage = "%d개 일치".formatted(matchedNumberCount);
        String bonusMatchMessage = ", 보너스 볼 일치";
        String prizeMessage = " (%s원)".formatted(new DecimalFormat().format(money));
        return matchCountMessage + (isBonusMatched ? bonusMatchMessage : "") + prizeMessage;
    }

    @Override
    public int compareTo(IPrizeOutput obj) {
        return Integer.compare(money, obj.money());
    }
}