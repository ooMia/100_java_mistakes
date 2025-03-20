package com.example.mistakes.api.questions;

import com.example.mistakes.base.type.FsMeta;
import com.example.mistakes.base.type.Identifiable;
import com.example.mistakes.base.type.Message;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;

// TODO: refactor to class
@Log4j2
public record QuestionEntity(String message) implements Message, Identifiable<String>, FsMeta {

  public Number getChapter() {
    var data = new HashMap<String, Number>();
    data.put("expression", 2);

    var topLevelPackage = message.split("\\.")[3];

    return data.getOrDefault(topLevelPackage, 0);
  }

  public String getId() {
    // TODO: refactor using getChapter()
    var data = new HashMap<String, Number>();
    data.put("expression", 2);
    var topLevelPackage = message.split("\\.")[3];
    var chapter = data.getOrDefault(topLevelPackage, 0);

    // new: return 1-2 when got _01_OperationPriority
    // when getting `com.example.mistakes.expression._01_OperationPriority.Ex2`
    // return id = "_01_2"
    Pattern pattern = Pattern.compile("_(\\d+)_\\S+(\\d+)");
    Matcher matcher = pattern.matcher(message);
    if (matcher.find()) {
      String mistakeId = matcher.group(1);
      String index = matcher.group(2);
      return "_%s_%s".formatted(mistakeId, index);
    }

    // legacy: return 1 when got `com/example/mistakes/expression/_01_OperationPriority.java`
    String filtered = message.replaceAll("[^0-9]", "");
    if (filtered.isEmpty()) {
      return String.valueOf(message.hashCode());
    }
    return String.valueOf(Integer.parseInt(filtered));
  }

  public Path getPath() {
    // return = src/main/java/com/example/mistakes/expression/_01_OperationPriority.java
    // when message = com.example.mistakes.expression._01_OperationPriority.Ex2

    String pathPrefix = "src/main/java";
    String fileExtension = ".java";

    Pattern pattern = Pattern.compile("(\\S+_\\d+_[^.]+)");
    Matcher matcher = pattern.matcher(message);

    String path;
    if (matcher.find()) {
      String group = matcher.group(1);
      path = group.replace(".", "/") + fileExtension;
    } else {
      throw new IllegalArgumentException("Matcher not found: message=" + message);
    }
    return Paths.get(pathPrefix, path);
  }

  public String getClassName() {
    // return = Ex2
    // when message = com.example.mistakes.expression._01_OperationPriority.Ex2

    // temporary assume there is only one nested class
    return message.substring(message.lastIndexOf(".") + 1);
  }

  public String getBefore() {
    return Code.readMethodCode(getPath(), getClassName(), "before");
  }

  public String getAfter() {
    return Code.readMethodCode(getPath(), getClassName(), "after");
  }

  private class Code {
    static String readMethodCode(Path filePath, String className, String methodName) {
      try {
        return read(filePath, className, methodName);
      } catch (Exception e) {
        return "NoSuchFileException path=%s methodName=%s"
            .formatted(filePath, className, methodName);
      }
    }

    private static String read(Path filePath, String className, String methodName)
        throws IOException {

      List<String> classCode = findClassCode(filePath, className);

      StringBuilder methodCode = new StringBuilder();
      boolean methodFound = false;
      for (String line : classCode) {
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

    private static List<String> findClassCode(Path filePath, String className) throws IOException {
      List<String> classCode = new ArrayList<>();

      boolean classFound = false;
      int braceCount = 0;
      for (String line : Files.readAllLines(filePath)) {
        if (line.contains("class " + className)) { // Simple check for class signature
          classFound = true;
          classCode.add(line);
          braceCount++;
        } else if (classFound) {
          classCode.add(line);
          if (line.contains("{")) {
            braceCount++;
          }
          if (line.contains("}")) {
            braceCount--;
            if (braceCount == 0) {
              break; // Stop reading after the closing brace of the class
            }
          }
        }
      }
      return classCode;
    }
  }
}
