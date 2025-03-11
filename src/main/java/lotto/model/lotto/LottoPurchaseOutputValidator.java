package lotto.model.lotto;

import base.model.Validator;
import lotto.model.lotto.winning.LottoOutputValidator;

public class LottoPurchaseOutputValidator implements Validator<ILottoPurchaseOutput> {
    private final Validator<ILottoOutput> validator = new LottoOutputValidator();

    @Override
    public void validate(ILottoPurchaseOutput output) throws IllegalArgumentException {
        if (output == null) {
            throw new IllegalArgumentException("output == null");
        }
        if (output.purchased() == null) {
            throw new IllegalArgumentException("output.purchased() == null");
        }
        if (output.purchased().isEmpty()) {
            throw new IllegalArgumentException("output.purchased().isEmpty()");
        }
        output.purchased().forEach(validator::validate);
    }

}
