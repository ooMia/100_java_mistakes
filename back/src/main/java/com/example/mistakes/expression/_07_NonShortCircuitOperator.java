package com.example.mistakes.expression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mistakes.QuestionEntityBuilder;
import com.example.mistakes.service.QuestionService;

@Component
public class _07_NonShortCircuitOperator {

  _07_NonShortCircuitOperator(@Autowired QuestionService service) {
    var entities = QuestionEntityBuilder.of(Ex1.class, Ex2.class, Ex3.class);
    service.addAll(entities);
  }

  static class Ex1 {

    boolean before(int[] data, int index) {
      return index >= 0 && index < data.length & data[index] > 0;
    }

    boolean after(int[] data, int index) {
      return index >= 0 && index < data.length && data[index] > 0;
    }
  }

  static class Ex2 {

    boolean before(boolean check1, boolean check2, boolean check3) {
      boolean result = true;
      result &= check1;
      result &= check2;
      result &= check3;
      return result;
    }

    boolean after(boolean check1, boolean check2, boolean check3) {
      boolean result = true;
      result = result && check1;
      result = result && check2;
      result = result && check3;
      return result;
    }
  }

  static class Ex3 {

    interface First {
    }

    interface Second {
    }

    interface Third {
    }

    interface Exclude extends Second {
    }

    static boolean checkFirst(First obj) {
      return true;
    }

    static boolean checkThird(Third obj) {
      return true;
    }

    boolean before(Object obj) {
      return obj instanceof First && checkFirst((First) obj) |
          obj instanceof Second && !(obj instanceof Exclude) ||
          obj instanceof Third && checkThird((Third) obj);
    }
  
    boolean after(Object obj) {
      return switch (obj) { // Java 21
        case First first -> checkFirst(first);
        case Second second -> !(second instanceof Exclude);
        case Third third -> checkThird(third);
        case null, default -> false;
      };
    }
  }
}
