package com.example.mistakes.service;

import com.example.mistakes.api.questions.QuestionEntity;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService<QuestionEntity> {

  @Autowired private final ReadOnlyFsRepository<QuestionEntity, String> repository;

  @Override
  public void add(QuestionEntity entity) {
    repository.save(entity);
  }

  @Override
  public QuestionEntity find(Number chapter, Number index) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  @Override
  public QuestionEntity findById(String id) throws NoSuchElementException {
    return repository.findById(id).orElseThrow();
  }

  @Override
  public List<QuestionEntity> findAll() {
    List<QuestionEntity> res = new java.util.ArrayList<QuestionEntity>();
    repository.findAll().forEach(res::add);
    return res;
  }

  @Override
  public List<QuestionEntity> findByChapter(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByChapter'");
  }

  @Override
  public List<QuestionEntity> findByChapter(Number index) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByChapter'");
  }

  @Override
  public List<QuestionEntity> findByKeywords(String keyword) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByKeywords'");
  }

  @Override
  public List<QuestionEntity> findByKeywords(Iterable<String> keywords, ConditionType type) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByKeywords'");
  }
}
