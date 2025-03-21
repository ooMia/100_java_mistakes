package com.example.mistakes.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mistakes.service.QuestionService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
class _01_OperationPriorityTests {

  @Autowired
  private QuestionService service;

  // need post-processing to remove redundant spaces
  // i.g. double-spaces or tab or newline to single space
  private String normalize(String code) {
    return code.replaceAll("\\s+", " ");
  }

  // TODO: move to BaseAPITests
  @Test
  void testRegistration() {
    var message = "com.example.mistakes.expression._01_OperationPriority.Ex1";
    var classPath = "com/example/mistakes/expression/_01_OperationPriority.java";
    var before = "int before(short lo, short hi) { return lo << 16 + hi; }";
    var after = "int after(short lo, short hi) { return (lo << 16) + hi; }";
    var entity = service.findOne("2_01_1");
    assertEquals(entity.getId(), "2_01_1");
    assertEquals(entity.message(), message);
    assertEquals(entity.getPath().toString(), "src/main/java/" + classPath);

    assertTrue(normalize(entity.getBefore()).contains(before));
    assertTrue(normalize(entity.getAfter()).contains(after));
  }

  @Test
  void testRegistration2() {
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

    var entity = service.findOne("2_01_2");
    // var entity = service.find(2, 2);
    assertEquals(entity.message(), message);
    assertEquals(entity.getId(), "2_01_2");
    assertEquals(entity.getPath().toString(), "src/main/java/" + classPath);

    assertTrue(normalize(entity.getBefore()).contains(before));
    assertTrue(normalize(entity.getAfter()).contains(after));
  }

  @ParameterizedTest
  @MethodSource("dualCombinations")
  void testEx1(short lo, short hi) {
    var target = new _01_OperationPriority.Ex1();
    int expected = lo * (int) Math.pow(2, 16) + hi;
    assertEquals(expected, target.after(lo, hi), "Test failed with lo=%d hi=%d".formatted(lo, hi));
  }

  static Stream<Arguments> dualCombinations() {
    var values = List.of((short) 0, (short) 1, (short) 2, Short.MAX_VALUE);
    return values.stream().flatMap(lo -> values.stream().map(hi -> Arguments.of(lo, hi)));
  }

  @Test
  void testEx2() {
    var target = new _01_OperationPriority.Ex2();

    printBinary(1);
    printBinary(1 + 1);
    printBinary(1 + 1 << 8);
    printBinary(1 + 1 << 8 + 1);
    printBinary(1 + 1 << 8 + 1 << 16);
    printBinary(1 + 1 << 8 + 1 << 16 + 1);
    printBinary(1 + 1 << 8 + 1 << 16 + 1 << 24);
    printBinary(target.after());

    assertEquals(target.before(), 0);
  }

  private static void printBinary(int value) {
    printBinary(value, 32);
  }

  private static void printBinary(int value, int length) {
    System.out.println(
        String.format("%" + length + "s", Integer.toBinaryString(value)).replace(' ', '0'));
  }

  @Test
  void testEx3() {
    var target = new _01_OperationPriority.Ex3();

    assertEquals(target.before(), target.BLOCK_SIZE * 0.5);
    assertEquals(target.after(), target.BLOCK_SIZE * 1.25);
  }

  @Test
  void testEx4() {
    var target = new _01_OperationPriority.Ex4();
    var input = 0x0FF0;

    printBinary(input, 16);
    printBinary(target.before(input), 16);
    printBinary(target.after(input), 16);

    assertEquals(target.before(input) % 2, input % 2);
    assertEquals(target.after(input) % 2, 1);
  }
}
