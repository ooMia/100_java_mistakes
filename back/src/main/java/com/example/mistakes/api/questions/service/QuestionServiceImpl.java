package com.example.mistakes.api.questions.service;

import com.example.mistakes.api.questions.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService<QuestionEntity, Long> {

  @Autowired private final ReadOnlyFsRepository<QuestionEntity, Long> repository;

  @Override
  public QuestionEntity findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public Iterable<QuestionEntity> findByChapter(String chapter) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<QuestionEntity> findByChapter(Long id) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<QuestionEntity> findByKeywords(String keyword) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<QuestionEntity> findByKeywords(Iterable<String> keywords, ConditionType type) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
