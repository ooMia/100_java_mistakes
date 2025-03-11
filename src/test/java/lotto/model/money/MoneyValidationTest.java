package lotto.model.money;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyValidationTest {

    @DisplayName("edge case")
    @ParameterizedTest
    @ValueSource(ints = {-1})
    void outOfRange(int number) {
        // when
        Executable lambda = () -> MoneyInputDto.of(number);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, lambda, "input.money() < 0");
    }

}
