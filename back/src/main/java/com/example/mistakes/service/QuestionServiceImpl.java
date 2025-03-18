package com.example.mistakes.service;

import com.example.mistakes.api.questions.QuestionEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService<QuestionEntity> {

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
  public List<QuestionEntity> findAll() {
    List<QuestionEntity> res = new java.util.ArrayList<QuestionEntity>();
    repository.findAll().forEach(res::add);
    return res;
  }

  @Override
  public List<QuestionEntity> findByChapter(String chapter) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByChapter'");
  }

  @Override
  public List<QuestionEntity> findByChapter(Integer number) {
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
