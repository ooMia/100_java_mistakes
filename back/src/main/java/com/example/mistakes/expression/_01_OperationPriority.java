package com.example.mistakes.expression;

import com.example.mistakes.api.questions.QuestionEntity;
import com.example.mistakes.api.questions.service.QuestionService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class _01_OperationPriority {

  List<String> sourceBefore =
      List.of("int before (short lo, short hi) {" + "  return lo << 16 + hi;" + "}");
  List<String> sourceAfter =
      List.of("int after (short lo, short hi) {" + "  return (lo << 16) + hi;" + "}");
  QuestionEntity entity =
      new QuestionEntity(
          this.getClass().getSimpleName(),
          String.join("\n", sourceBefore),
          String.join("\n", sourceAfter));

  public _01_OperationPriority(QuestionService<QuestionEntity> service) {
    service.add(entity);
  }

  int before(short lo, short hi) {
    return lo << 16 + hi;
  }

  int after(short lo, short hi) {
    return (lo << 16) + hi;
  }
}
