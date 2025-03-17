package com.example.mistakes.api.questions.service;

interface QuestionService<T, ID> {

  // 1. query by question id
  T findById(ID id);

  // 2. chapter
  Iterable<T> findByChapter(String chapter);

  Iterable<T> findByChapter(ID id);

  // 3. keywords
  enum ConditionType {
    AND,
    OR,
  }

  Iterable<T> findByKeywords(String keyword);

  Iterable<T> findByKeywords(Iterable<String> keywords, ConditionType type);
}
