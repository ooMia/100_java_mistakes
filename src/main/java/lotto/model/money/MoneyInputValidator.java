package lotto.model.money;

import base.model.Validator;

public class MoneyInputValidator implements Validator<IMoneyInput> {
    @Override
    public void validate(IMoneyInput input) throws IllegalArgumentException {
        if (input.money() < 0) {
            throw new IllegalArgumentException("input.money() < 0");
        }
    }
}
