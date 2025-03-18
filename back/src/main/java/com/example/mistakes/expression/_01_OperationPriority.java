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
  }

  int before(short lo, short hi) {
    return lo << 16 + hi;
  }

  int after(short lo, short hi) {
    return (lo << 16) + hi;
  }
}
