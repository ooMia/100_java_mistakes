package com.example.mistakes.api.questions;

import com.example.mistakes.base.type.definition.ID;
import com.example.mistakes.base.type.definition.Message;

public record QuestionEntity(String message, String sourceBefore, String sourceAfter)
    implements Message, ID {

  public Number getId() {
    String filtered = message.replaceAll("[^0-9]", "");
    if (filtered.isEmpty()) {
      return message.hashCode() - 1;
    }
    Number id = Integer.parseInt(filtered);
    return id;
  }
}
