package com.example.mistakes;

import com.example.mistakes.api.questions.QuestionEntity;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionEntityBuilder<T> {
  public QuestionEntity build(Class<T> cls) {
    return new QuestionEntity(cls.getCanonicalName());
  }

  public static QuestionEntity of(Class<?> cls) {
    return new QuestionEntity(cls.getCanonicalName());
  }

  public static Iterable<QuestionEntity> of(Class<?>... cls) {
    return List.of(cls).stream().map(QuestionEntityBuilder::of).collect(Collectors.toList());
  }
}
