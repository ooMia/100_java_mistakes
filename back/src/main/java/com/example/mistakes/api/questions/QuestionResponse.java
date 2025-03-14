package com.example.mistakes.api.questions;

import com.example.mistakes.base.type.definition.ResponseMany;

record QuestionResponse(QuestionEntity[] result, int length) implements ResponseMany<Object> {}
