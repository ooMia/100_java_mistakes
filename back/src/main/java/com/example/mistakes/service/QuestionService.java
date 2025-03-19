package com.example.mistakes.service;

import java.util.List;

public interface QuestionService<T> {

  void add(T entity);

  List<T> findAll();

  T find(Number chapter, Number index);

  // 1. query by question id
  T findById(String id);

  // 2. chapter
  List<T> findByChapter(String name);

  List<T> findByChapter(Number index);

  // 3. keywords
  enum ConditionType {
    AND,
    OR,
  }

  List<T> findByKeywords(String keyword);

  List<T> findByKeywords(Iterable<String> keywords, ConditionType type);
}
