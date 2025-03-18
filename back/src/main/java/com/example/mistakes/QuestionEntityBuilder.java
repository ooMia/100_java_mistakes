package com.example.mistakes;

import com.example.mistakes.api.questions.QuestionEntity;

public class QuestionEntityBuilder<T> {
  public QuestionEntity build(Class<T> cls) {
    String pkgName = cls.getPackageName();
    String clsName = cls.getSimpleName();
    return new QuestionEntity("%s/%s.java".formatted(pkgName, clsName));
  }
}
