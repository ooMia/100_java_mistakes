package com.example.mistakes.api.questions.service;

import com.example.mistakes.api.questions.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class QuestionServiceImpl implements QuestionService<QuestionEntity> {

  @Autowired private final ReadOnlyFsRepository<QuestionEntity> repository;

  @Override
  public void add(QuestionEntity entity) {
    repository.save(entity);
  }

  @Override
  public QuestionEntity findById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public Iterable<QuestionEntity> findByChapter(String chapter) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByChapter'");
  }

  @Override
  public Iterable<QuestionEntity> findByChapter(Integer number) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByChapter'");
  }

  @Override
  public Iterable<QuestionEntity> findByKeywords(String keyword) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByKeywords'");
  }

  @Override
  public Iterable<QuestionEntity> findByKeywords(Iterable<String> keywords, ConditionType type) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByKeywords'");
  }
}
