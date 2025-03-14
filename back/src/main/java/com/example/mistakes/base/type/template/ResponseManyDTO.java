package com.example.mistakes.base.type.template;

import com.example.mistakes.base.type.definition.ResponseMany;

public abstract class ResponseManyDTO<T> {

  private final ResponseManyImpl impl;

  public ResponseManyDTO(T[] data) {
    this.impl = new ResponseManyImpl(data);
  }

  private class ResponseManyImpl implements ResponseMany<T> {

    final T[] data;

    ResponseManyImpl(T[] data) {
      this.data = data;
    }

    @Override
    public T[] result() {
      return data;
    }

    @Override
    public int length() {
      return data.length;
    }
  }

  public T[] getResult() {
    return impl.result();
  }

  public int getLength() {
    return impl.length();
  }
}
