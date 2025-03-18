package com.example.mistakes.service;

import java.util.List;

public interface QuestionService<T> {

  void add(T entity);

  List<T> findAll();

  // 1. query by question id
  T findById(Integer id);

  // 2. chapter
  List<T> findByChapter(String chapter);

  List<T> findByChapter(Integer number);

  // 3. keywords
  enum ConditionType {
    AND,
    OR,
  }

  List<T> findByKeywords(String keyword);

  List<T> findByKeywords(Iterable<String> keywords, ConditionType type);
}
