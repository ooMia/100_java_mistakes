package com.example.mistakes.api.questions;

import com.example.mistakes.base.type.FsMeta;
import com.example.mistakes.base.type.ID;
import com.example.mistakes.base.type.Message;
import java.nio.file.Path;
import java.nio.file.Paths;

public record QuestionEntity(String message) implements Message, ID, FsMeta {

  public Number getId() {
    String filtered = message.replaceAll("[^0-9]", "");
    if (filtered.isEmpty()) {
      return message.hashCode() - 1;
    }
    Number id = Integer.parseInt(filtered);
    return id;
  }

  public Path getPath() {
    String prefix = "src/main/java";
    return Paths.get(prefix, message);
  }
}
