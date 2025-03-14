package com.example.mistakes.base.type.definition;

public interface ResponseMany<T> {
  T[] result();

  int length();
}
