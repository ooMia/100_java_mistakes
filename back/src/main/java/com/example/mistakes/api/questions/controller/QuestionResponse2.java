package com.example.mistakes.api.questions.controller;

import com.example.mistakes.api.questions.QuestionEntity;
import com.example.mistakes.base.type.template.ResponseManyDTO;

final class QuestionResponse2 extends ResponseManyDTO<QuestionEntity> {
  QuestionResponse2(QuestionEntity[] result) {
    super(result);
  }
}
