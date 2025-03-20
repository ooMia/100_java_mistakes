package com.example.mistakes.service;

import com.example.mistakes.api.questions.QuestionEntity;
import java.util.List;

public interface QuestionService {
  // Register
  void add(QuestionEntity entity);

  void addAll(Iterable<QuestionEntity> entity);

  // find one
  QuestionEntity findOne(String id);

  QuestionEntity findOne(String chapterName, Integer mistakeId, Integer exampleId);

  QuestionEntity findOne(Integer chapterNumber, Integer mistakeId, Integer exampleId);

  // find many
  List<QuestionEntity> findAll();

  List<QuestionEntity> findAllByChapterName(String name);

  List<QuestionEntity> findAllByChapterNumber(Number number);

  List<QuestionEntity> findAllByMistakeId(Number id);
}
