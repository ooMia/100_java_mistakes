package com.example.mistakes.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.mistakes.api.questions.QuestionEntity;
import com.example.mistakes.api.questions.service.QuestionService;
import java.util.Random;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class _01_OperationPriorityTests {

  @Autowired private QuestionService<QuestionEntity> service;
  @Autowired private _01_OperationPriority target;

  @Test
  void testRegistration() {
    assertEquals(target.entity, service.findById(1));
  }

  @ParameterizedTest
  @MethodSource("randomArguments")
  @DisplayName("Fuzz test with random inputs using ParameterizedTest")
  void fuzzTest(short lo, short hi) {
    assertEquals(
        target.after(lo, hi),
        lo * Math.pow(2, 16) + hi,
        "Fuzz test failed with lo=%d hi=%d".formatted(lo, hi));
  }

  static Stream<Arguments> randomArguments() {
    Random random = new Random();
    return Stream.generate(
            () ->
                Arguments.of(
                    (short) random.nextInt(Short.MAX_VALUE),
                    (short) random.nextInt(Short.MAX_VALUE)))
        .limit(100);
  }
}
