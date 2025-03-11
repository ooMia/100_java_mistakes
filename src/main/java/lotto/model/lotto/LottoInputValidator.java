package lotto.model.lotto;

import base.model.Validator;
import java.util.TreeSet;
import lotto.model.lotto.winning.LottoOutputValidator;

public class LottoInputValidator implements Validator<ILottoInput> {
    private final Validator<ILottoOutput> outputValidator = new LottoOutputValidator();

    @Override
    public void validate(ILottoInput input) throws IllegalArgumentException {
        if (input.numbers() == null) {
            throw new IllegalArgumentException("input.numbers() == null");
        }
        if (input.numbers().size() != 6) {
            throw new IllegalArgumentException("input.numbers().size() != 6");
        }
        var numbers = new TreeSet<>(input.numbers());
        outputValidator.validate(LottoOutputDto.of(numbers));
    }
}
