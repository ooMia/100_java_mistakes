package com.example.mistakes.expression;

import com.example.mistakes.QuestionEntityBuilder;
import com.example.mistakes.api.questions.QuestionEntity;
import com.example.mistakes.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class _01_OperationPriority {

  _01_OperationPriority(@Autowired QuestionService<QuestionEntity> service) {
    var builder = new QuestionEntityBuilder<_01_OperationPriority>();
    service.add(builder.build(_01_OperationPriority.class));

    service.add(new QuestionEntityBuilder<Ex2>().build(Ex2.class));
  }

  int before(short lo, short hi) {
    return lo << 16 + hi;
  }

  int after(short lo, short hi) {
    return (lo << 16) + hi;
  }

  class Ex2 {
    String subContext = "Binary Shift";
    short xmin = 1, ymin = 1, xmax = 1, ymax = 1;

    int before() {
      return xmin + ymin << 8 + xmax << 16 + ymax << 24;
    }

    int after() {
      return xmin + (ymin << 8) + (xmax << 16) + (ymax << 24);
    }
  }
}
