package lotto.model.prize;

import base.model.Validator;
import lotto.model.money.IMoneyInput;
import lotto.model.money.MoneyInputDto;
import lotto.model.money.MoneyInputValidator;

public class PrizeOutputValidator implements Validator<IPrizeOutput> {
    private final Validator<IMoneyInput> validator = new MoneyInputValidator();

    @Override
    public void validate(IPrizeOutput input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("input == null");
        }
        if (input.matchedNumberCount() < 0 || input.matchedNumberCount() > 6) {
            throw new IllegalArgumentException("input.matchedNumberCount() < 0 || input.matchedNumberCount() > 6");
        }
        validator.validate(MoneyInputDto.of(input.money()));
    }

}
