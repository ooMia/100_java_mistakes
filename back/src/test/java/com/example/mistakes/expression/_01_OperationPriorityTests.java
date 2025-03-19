package com.example.mistakes.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.mistakes.api.questions.QuestionEntity;
import com.example.mistakes.service.QuestionService;
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

  // need post-processing to remove redundant spaces
  // i.g. double-spaces or tab or newline to single space
  private String normalize(String code) {
    return code.replaceAll("\\s+", " ");
  }

  @Test
  void testRegistration() {
    var classPath = "com/example/mistakes/expression/_01_OperationPriority.java";
    var before = "int before(short lo, short hi) { return lo << 16 + hi; }";
    var after = "int after(short lo, short hi) { return (lo << 16) + hi; }";
    var entity = service.findById("1");
    assertEquals(entity.message(), classPath);
    assertEquals(entity.getId(), "1");
    assertEquals(entity.getPath().toString(), "src/main/java/" + classPath);

    assertTrue(normalize(entity.getBefore()).contains(before));
    assertTrue(normalize(entity.getAfter()).contains(after));
  }

  @Test
  void testEx2() {
    var message = "com.example.mistakes.expression._01_OperationPriority.Ex2";
    var classPath = "com/example/mistakes/expression/_01_OperationPriority.java";
    var before = "int before() { return xmin + ymin << 8 + xmax << 16 + ymax << 24; }";
    var after = "int after() { return xmin + (ymin << 8) + (xmax << 16) + (ymax << 24); }";

    // var entity = service.findAll().get(1);
    // System.out.println(entity);
    // System.out.println(entity.message());
    // System.out.println(entity.getId());
    // System.out.println(entity.getPath().toString());
    // System.out.println(entity.getBefore());
    // System.out.println(entity.getAfter());

    var entity = service.findById("_01_2");
    // var entity = service.find(2, 2);
    assertEquals(entity.message(), message);
    assertEquals(entity.getId(), "_01_2");
    assertEquals(entity.getPath().toString(), "src/main/java/" + classPath);

    assertTrue(normalize(entity.getBefore()).contains(before));
    assertTrue(normalize(entity.getAfter()).contains(after));
  }

  @ParameterizedTest
  @MethodSource("randomArguments")
  @DisplayName("Fuzz test with random inputs using ParameterizedTest")
  void fuzzTest(short lo, short hi) {
    var target = new _01_OperationPriority.Ex1();
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
