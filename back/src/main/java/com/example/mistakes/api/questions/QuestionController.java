package com.example.mistakes.api.questions;


import com.example.mistakes.base.template.ResponseManyDTO;
import com.example.mistakes.base.type.ResponseMany;
import com.example.mistakes.service.QuestionService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
  @Autowired private final QuestionService<QuestionEntity> service;

  public QuestionController(QuestionService<QuestionEntity> service) {
    this.service = service;
  }

  @GetMapping("/t1")
  public ResponseEntity<ResponseMany<QuestionEntity>> t1() {
    final var data = this.service.findAll();
    final var dto = new ResRecordQuestion(data, data.size());
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/t2")
  public ResponseEntity<ResponseMany<QuestionEntity>> t2() {
    final var data = this.service.findAll();
    final var dto = new ResClassQuestion(data);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/t3")
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
