package lotto.model.lotto.winning;

import base.model.Validator;
import lotto.model.lotto.ILottoInput;
import lotto.model.lotto.LottoInputValidator;

public class WinningLottoInputValidator implements Validator<IWinningLottoInput> {
    private final Validator<ILottoInput> outputValidator = new LottoInputValidator();

    @Override
    public void validate(IWinningLottoInput input) throws IllegalArgumentException {
        outputValidator.validate(input);
    }
}
