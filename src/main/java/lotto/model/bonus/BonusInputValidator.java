package lotto.model.bonus;

import base.model.Validator;

public class BonusInputValidator implements Validator<IBonusInput> {
    @Override
    public void validate(IBonusInput input) throws IllegalArgumentException {
        var bonusNumber = input.bonusNumber();
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (input.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
