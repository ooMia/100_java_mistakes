package com.example.mistakes.base.template;

import com.example.mistakes.base.type.ResponseMany;
import java.util.List;
import lombok.Getter;

/**
 * Wrapper class for implementing `ResponseMany` interface while supporting compatability with
 * record-based DTOs
 *
 * @param <T> type of result
 * @see ResponseMany
 */
@Getter
public abstract class ResponseManyDTO<T> implements ResponseMany<T> {

  private final List<T> result;
  private final Number length;

  public ResponseManyDTO(Iterable<T> result) {
    this.result = (List<T>) result;
    this.length = this.result.size();
  }

  public final Number length() {
    return length;
  }

  public final Iterable<T> result() {
    return result;
  }
}
