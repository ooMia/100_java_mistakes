package com.example.mistakes.api.questions;

import com.example.mistakes.base.type.definition.ResponseMany;
import com.example.mistakes.base.type.template.ResponseManyDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

  private final List<QuestionEntity> data =
      // TODO: get data from service
      List.of(
          new QuestionEntity("A", "A", "A"),
          new QuestionEntity("B", "B", "B"),
          new QuestionEntity("C", "C", "C"),
          new QuestionEntity("D", "D", "D"));

  @GetMapping("/t1")
  public ResponseEntity<ResponseMany<QuestionEntity>> t1() {
    final var dto = new ResRecordQuestion(this.data, this.data.size());
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/t2")
  public ResponseEntity<ResponseMany<QuestionEntity>> t2() {
    final var dto = new ResClassQuestion(this.data);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/t3")
  public ResponseEntity<ResponseMany<QuestionEntity>> t3() {
    final var dto = ResClassOfQuestion.of(this.data);
    return ResponseEntity.ok().body(dto);
  }
}

// Type 1: record
record ResRecordQuestion(List<QuestionEntity> result, Integer length)
    implements ResponseMany<QuestionEntity> {}

// Type 2: custom wrapper DTO
final class ResClassQuestion extends ResponseManyDTO<QuestionEntity> {
  ResClassQuestion(Iterable<QuestionEntity> result) {
    super(result);
  }
}

// Type 3: custom wrapper DTO with static of
final class ResClassOfQuestion {

  private static class innerImpl extends ResponseManyDTO<QuestionEntity> {
    innerImpl(Iterable<QuestionEntity> result) {
      super(result);
    }
  }

  static ResponseManyDTO<QuestionEntity> of(Iterable<QuestionEntity> result) {
    return new innerImpl(result);
  }
}
