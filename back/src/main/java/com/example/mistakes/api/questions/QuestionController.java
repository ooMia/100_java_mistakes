package com.example.mistakes.api.questions;

import com.example.mistakes.base.template.ResponseManyDTO;
import com.example.mistakes.base.type.ResponseMany;
import com.example.mistakes.service.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuestionController {
  @Autowired private final QuestionService<QuestionEntity> service;

  public QuestionController(QuestionService<QuestionEntity> service) {
    this.service = service;
  }

  // TODO: assign endpoint for each example by id: _chpater_index
  // GET /api/questions/1 -> _02_1
  // GET /api/expression/n -> _02_n

  @GetMapping("/questions/{id}")
  public ResponseEntity<ResponseMany<QuestionEntity>> findOneByMistakeId(
      @PathVariable("id") int id) {
    // TODO: find by id should automatically handle the prefix
    final var data = this.service.findAll().get(id - 1);
    final var dto = new ResRecordQuestion(List.of(data), 1);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/expression/{index}")
  public ResponseEntity<ResponseMany<QuestionEntity>> findOneInExpressionByIndex(
      @PathVariable("index") int index) {
    final var data = this.service.findById("_02_%d".formatted(index));
    final var dto = new ResClassQuestion(List.of(data));
    return ResponseEntity.ok().body(dto);
  }

  // legacy
  @GetMapping("/questions/t3")
  public ResponseEntity<ResponseMany<QuestionEntity>> t3() {
    final var data = this.service.findAll();
    final var dto = ResClassOfQuestion.of(data);
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
