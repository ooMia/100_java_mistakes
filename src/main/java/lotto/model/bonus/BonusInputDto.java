package lotto.model.bonus;

import java.util.SortedSet;

public record BonusInputDto(
        SortedSet<Integer> numbers,
        int bonusNumber
) implements IBonusInput {
    public static BonusInputDto of(SortedSet<Integer> lottoNumbers, int number) {
        var tmp = new BonusInputDto(lottoNumbers, number);
        new BonusInputValidator().validate(tmp);
        return tmp;
    }
}
