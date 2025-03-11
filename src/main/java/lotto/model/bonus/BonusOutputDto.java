package lotto.model.bonus;

import lotto.model.lotto.ILottoOutput;

public record BonusOutputDto(int bonusNumber) implements IBonusOutput {
    public static BonusOutputDto of(int bonusNumber) {
        return new BonusOutputDto(bonusNumber);
    }

    public static BonusOutputDto of(IBonusInput input) {
        return new BonusOutputDto(input.bonusNumber());
    }

    public static BonusOutputDto of(IBonusOutput bonusNumber) {
        return new BonusOutputDto(bonusNumber.bonusNumber());
    }

    @Override
    public String toString() {
        return Integer.toString(bonusNumber);
    }

    public Boolean isBonusMatched(ILottoOutput bonus) {
        return bonus.numbers().contains(bonusNumber);
    }
}
