package com.example.mistakes.api.questions.service;

import org.springframework.stereotype.Service;

import com.example.mistakes.api.questions.QuestionEntity;

@Service
public class QuestionServiceImpl implements QuestionService<QuestionEntity, Long> {
  private final ReadOnlyFsRepository<QuestionEntity, Long> repository;

  public QuestionServiceImpl(ReadOnlyFsRepository<QuestionEntity, Long> repository) {
    this.repository = repository;
  }

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
