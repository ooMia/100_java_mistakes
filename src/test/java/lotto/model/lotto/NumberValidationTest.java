package lotto.model.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberValidationTest {
    private final Random random = new Random();
    private final List<Integer> mayDuplicatedInput = new ArrayList<>();
    private final SortedSet<Integer> mustNotDuplicatedInput = new TreeSet<>();

    @BeforeEach
    void setUp() {
        while (mayDuplicatedInput.size() < 5) {
            mayDuplicatedInput.add(random.nextInt(1, 45));
        }
        while (mustNotDuplicatedInput.size() < 5) {
            mustNotDuplicatedInput.add(random.nextInt(1, 45));
        }
    }

    @DisplayName("valid size")
    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    void invalidSize(int size) {
        // given
        while (mustNotDuplicatedInput.size() < size) {
            mustNotDuplicatedInput.add(random.nextInt(1, 45));
        }
        List<Integer> input = mustNotDuplicatedInput.stream().toList();
        // when
        Executable lambda = () -> LottoInputDto.of(input);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, lambda, "input.size() != 6");
    }

    @DisplayName("duplicated input")
    @Test
    void duplication() {
        // given
        int duplicatedNumber = mayDuplicatedInput.get(0);
        mayDuplicatedInput.add(duplicatedNumber);
        // when
        Executable lambda = () -> LottoInputDto.of(mayDuplicatedInput);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, lambda, "input.contains(duplicatedNumber)");
    }

    @DisplayName("edge case")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void outOfRange(int number) {
        // given
        mustNotDuplicatedInput.add(number);
        List<Integer> input = mustNotDuplicatedInput.stream().toList();
        // when
        Executable lambda = () -> LottoInputDto.of(input);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, lambda, "number < 1 || number > 45");
    }

}
