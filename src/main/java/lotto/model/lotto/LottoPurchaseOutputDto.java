package lotto.model.lotto;

import java.util.List;

public record LottoPurchaseOutputDto(
        List<ILottoOutput> purchased
) implements ILottoPurchaseOutput {
    public static LottoPurchaseOutputDto of(
            List<ILottoOutput> input
    ) throws IllegalArgumentException {
        return new LottoPurchaseOutputDto(input);
    }

    @Override
    public String toString() {
        return purchased.toString();
    }
}
