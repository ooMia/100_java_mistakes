package com.example.mistakes.api.questions.controller;

import com.example.mistakes.api.questions.QuestionEntity;
import com.example.mistakes.base.type.definition.ResponseMany;

record QuestionResponse(QuestionEntity[] result, int length) implements ResponseMany<Object> {}
