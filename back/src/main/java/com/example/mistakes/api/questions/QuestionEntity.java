package com.example.mistakes.api.questions;

import com.example.mistakes.base.type.FsMeta;
import com.example.mistakes.base.type.ID;
import com.example.mistakes.base.type.Message;
import java.io.IOException;
import java.nio.file.Files;
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

  public String getBefore() {
    return Code.readMethodCode(getPath(), "before");
  }

  public String getAfter() {
    return Code.readMethodCode(getPath(), "after");
  }

  private class Code {
    static String readMethodCode(Path path, String methodName) {
      try {
        return read(path, methodName);
      } catch (Exception e) {
        return methodName;
      }
    }

    private static String read(Path path, String methodName) throws IOException {
      StringBuilder methodCode = new StringBuilder();
      boolean methodFound = false;
      for (String line : Files.readAllLines(path)) {
        if (line.contains(" " + methodName + "(")) { // Simple check for method signature
          methodFound = true;
          methodCode.append(line).append("\n");
        } else if (methodFound && line.contains("}")) {
          methodCode.append(line).append("\n");
          break; // Stop reading after the closing brace of the method
        } else if (methodFound) {
          methodCode.append(line).append("\n");
        }
      }
      return methodCode.toString();
    }
  }
}
