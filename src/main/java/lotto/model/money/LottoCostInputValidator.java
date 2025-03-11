package lotto.model.money;

import base.model.Validator;

public class LottoCostInputValidator extends MoneyInputValidator implements Validator<IMoneyInput> {

    private final Validator<IMoneyInput> validator = new MoneyInputValidator();

    @Override
    public void validate(IMoneyInput input) throws IllegalArgumentException {
        validator.validate(input);
        if (input.money() == 0) {
            throw new IllegalArgumentException("input.money() == 0");
        }
        if (input.money() % 1000 != 0) {
            throw new IllegalArgumentException("input.money() % 1000 != 0");
        }
    }
}
