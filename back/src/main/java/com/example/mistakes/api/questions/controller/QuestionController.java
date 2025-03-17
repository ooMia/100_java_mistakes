package com.example.mistakes.api.questions.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mistakes.api.questions.QuestionEntity;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

  private final QuestionEntity[] data = {
    new QuestionEntity("A"),
    new QuestionEntity("B"),
    new QuestionEntity("C"),
    new QuestionEntity("D"),
  };

  // TODO: get data from service

  @GetMapping("/t1")
  public ResponseEntity<QuestionResponse> t1() {
    final var dto = new QuestionResponse(this.data, data.length);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/t2")
  public ResponseEntity<QuestionResponse2> t2() {
    final var dto = new QuestionResponse2(this.data);
    return ResponseEntity.ok().body(dto);
  }
}
