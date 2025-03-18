package com.example.mistakes.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.mistakes.api.questions.QuestionEntity;
import com.example.mistakes.service.QuestionService;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class _01_OperationPriorityTests {

  @Autowired private QuestionService<QuestionEntity> service;
  @Autowired private _01_OperationPriority target;

  @Test
  void testRegistration() {
    System.out.println(service.findAll());
    // assertEquals(target.entity, service.findById(1));
    for (Method method : target.getClass().getDeclaredMethods()) {
      System.out.println("Method Name: " + method.getName());
      System.out.println("Method Signature: " + method.toGenericString());

      try {
        // Get the code of the method by reading the source file
        Class<?> clazz = target.getClass();
        String className = clazz.getSimpleName();
        String methodName = method.getName();

        // Construct the path to the source file
        String sourceFilePath =
            "src/main/java/com/example/mistakes/expression/"
                + className
                + ".java"; // Adjust path if needed
        Path path = Path.of(sourceFilePath);

        // Read all lines from the source file
        String methodCode = readMethodCode(path, methodName);
        System.out.println("Method Code:\n" + methodCode);

      } catch (Exception e) {
        System.err.println(
            "Error reading code for method " + method.getName() + ": " + e.getMessage());
      }
    }
  }

  private String readMethodCode(Path path, String methodName) throws IOException {
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

  @ParameterizedTest
  @MethodSource("randomArguments")
  @DisplayName("Fuzz test with random inputs using ParameterizedTest")
  void fuzzTest(short lo, short hi) {
    assertEquals(
        target.after(lo, hi),
        lo * Math.pow(2, 16) + hi,
        "Fuzz test failed with lo=%d hi=%d".formatted(lo, hi));
  }

  static Stream<Arguments> randomArguments() {
    Random random = new Random();
    return Stream.generate(
            () ->
                Arguments.of(
                    (short) random.nextInt(Short.MAX_VALUE),
                    (short) random.nextInt(Short.MAX_VALUE)))
        .limit(100);
  }
}
