package com.example.mistakes.api.questions.service;

public interface QuestionService<T> {

  void add(T entity);

  // 1. query by question id
  T findById(Integer id);

  // 2. chapter
  Iterable<T> findByChapter(String chapter);

  Iterable<T> findByChapter(Integer number);

  // 3. keywords
  enum ConditionType {
    AND,
    OR,
  }

  Iterable<T> findByKeywords(String keyword);

  Iterable<T> findByKeywords(Iterable<String> keywords, ConditionType type);
}
