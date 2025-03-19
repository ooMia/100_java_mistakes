package com.example.mistakes;

import com.example.mistakes.api.questions.QuestionEntity;

public class QuestionEntityBuilder<T> {
  public QuestionEntity build(Class<T> cls) {
    return new QuestionEntity(cls.getCanonicalName());
  }
}
