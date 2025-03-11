package lotto.controller;

import base.controller.Converter;
import base.view.View;
import lotto.model.bonus.BonusOutputDto;
import lotto.model.bonus.IBonusOutput;
import lotto.model.lotto.ILottoPurchaseOutput;
import lotto.model.lotto.winning.IWinningLottoOutput;
import lotto.model.lotto.winning.WinningLottoOutputDto;
import lotto.model.prize.IPrizeOutput;
import lotto.model.prize.PrizeOutputDto;
import lotto.model.stats.IStatsOutput;
import lotto.model.stats.StatsInputDto;
import lotto.model.stats.StatsOutputDto;
import lotto.view.sout.PrizeStatisticsOutputView;

public class PurchaseLottoToPrize extends Converter<ILottoPurchaseOutput, IStatsOutput> {

    private final WinningLottoOutputDto winningLotto;
    private final BonusOutputDto winningBonusNumber;

    public PurchaseLottoToPrize(
            View<IStatsOutput> view,
            IWinningLottoOutput winningLotto,
            IBonusOutput winningBonusNumber
    ) {
        super(view);
        this.winningLotto = (WinningLottoOutputDto) winningLotto;
        this.winningBonusNumber = (BonusOutputDto) winningBonusNumber;
    }

    public static PurchaseLottoToPrize of(
            IWinningLottoOutput winningLotto,
            IBonusOutput bonus
    ) {
        return new PurchaseLottoToPrize(
                new PrizeStatisticsOutputView(),
                winningLotto,
                bonus
        );
    }

    @Override
    protected IStatsOutput convert(ILottoPurchaseOutput input) {
        var tmp = input.purchased().stream()
                .map(lotto -> PrizeOutputDto.of(lotto, winningLotto, winningBonusNumber))
                .map(e -> (IPrizeOutput) e).toList();
        return StatsOutputDto.of(StatsInputDto.of(tmp));
    }
}
