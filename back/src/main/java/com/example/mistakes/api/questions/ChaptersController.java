package com.example.mistakes.api.questions;

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
public class ChaptersController {
  @Autowired private final QuestionService service;

  public ChaptersController(QuestionService service) {
    this.service = service;
  }

  private ResponseEntity<ResponseMany<QuestionEntity>> _response(Iterable<QuestionEntity> data) {
    final var dto = new ResClassQuestion(data);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/{chapterName}")
  public ResponseEntity<ResponseMany<QuestionEntity>> getAllMistakesInChapter(
      @PathVariable String chapterName) {
    final var data = this.service.findAllByChapterName(chapterName);
    return _response(data);
  }

  @GetMapping("/{chapterName}/{mistakeId}")
  public ResponseEntity<ResponseMany<QuestionEntity>> getExamplesInMistake(
      @PathVariable String chapterName, @PathVariable Integer mistakeId) {
    final var data = this.service.findAllByMistakeId(mistakeId);
    return _response(data);
  }

  @GetMapping("/{chapterName}/{mistakeId}/{exampleId}")
  public ResponseEntity<ResponseMany<QuestionEntity>> getOneExample(
      @PathVariable String chapterName,
      @PathVariable Integer mistakeId,
      @PathVariable Integer exampleId) {
    final var data = this.service.findOne(chapterName, mistakeId, exampleId);
    return _response(List.of(data));
  }
}
