package lotto.model.bonus;

import java.util.List;
import java.util.TreeSet;
import lotto.model.lotto.LottoOutputDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusValidationTest {
    private final LottoOutputDto lotto = new LottoOutputDto(new TreeSet<>(List.of(1, 2, 3, 4, 5, 6)));

    @DisplayName("edge case")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void outOfRange(int number) {
        // given
        var lottoNumbers = lotto.numbers();
        // when
        Executable lambda = () -> BonusInputDto.of(lottoNumbers, number);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, lambda, "number < 1 || number > 45");
    }

    @DisplayName("duplicated input")
    @Test
    void duplication() {
        // given
        var lottoNumbers = lotto.numbers();
        int duplicatedNumber = lotto.numbers().first();
        // when
        Executable lambda = () -> BonusInputDto.of(lottoNumbers, duplicatedNumber);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, lambda, "input.contains(duplicatedNumber)");
    }
}
