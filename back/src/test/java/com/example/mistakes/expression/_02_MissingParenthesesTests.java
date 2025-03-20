package com.example.mistakes.expression;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class _02_MissingParenthesesTests {

  @Test
  void testEx1() {
    var target = new _02_MissingParentheses.Ex1();

    assertThrows(StringIndexOutOfBoundsException.class, () -> target.before(-1, " "));
    assertDoesNotThrow(() -> target.after(-1, " "));

    assertTrue(target.after(0, "\t"));
    assertTrue(target.after(1, "a "));
  }

  @Test
  void testEx2() {
    var target = new _02_MissingParentheses.Ex2();

    assertThrows(NegativeArraySizeException.class, () -> target.before("abc", -1));
    assertDoesNotThrow(() -> target.after("abc", -1));

    assertTrue(target.after("abc", 0).capacity() == 3);
  }

  @Test
  void testEx3() {
    var target = new _02_MissingParentheses.Ex3();

    var whenNull = "Value: (unknown)";
    assertNotEquals(target.before(null), whenNull);
    assertEquals(target.after(null), whenNull);
  }

  @Test
  void testEx4() {
    var target = new _02_MissingParentheses.Ex4();

    var init = List.of("a", "b", "c");

    assertEquals(target.before(init, null), 1);
    assertEquals(target.before(init, "d"), 1);
    
    assertEquals(target.after(init, null), 3);
    assertEquals(target.after(init, "d"), 4);
  }
}
