package lotto.model.lotto.winning;

import base.model.Validator;
import lotto.model.lotto.ILottoOutput;

public class LottoOutputValidator implements Validator<ILottoOutput> {

    @Override
    public void validate(ILottoOutput input) throws IllegalArgumentException {
        if (input.numbers() == null) {
            throw new IllegalArgumentException("input.numbers() == null");
        }
        if (input.numbers().size() != 6) {
            throw new IllegalArgumentException("input.numbers().size() != 6");
        }
        input.numbers().forEach(this::validate);
    }

    void validate(Integer element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("element == null");
        }
        if (element < 1 || element > 45) {
            throw new IllegalArgumentException("element < 1 || element > 45");
        }
    }
}
