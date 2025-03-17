package com.example.mistakes.base.type.definition;

public interface ResponseMany<T> {
  Iterable<T> result();

  Number length();
}
