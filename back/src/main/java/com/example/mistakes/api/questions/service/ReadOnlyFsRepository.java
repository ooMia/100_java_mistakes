package com.example.mistakes.api.questions.service;

import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * ReadOnlyFsRepository
 *
 * <p>Repository interface for read-only operations
 *
 * @param <T> Entity
 * @param <ID> Entity ID type
 * @see org.springframework.data.repository.Repository
 */
@Repository
class ReadOnlyFsRepository<T, ID> implements CrudRepository<T, ID>, QueryByExampleExecutor<T> {

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void delete(T entity) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAll() {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAll(Iterable<? extends T> entities) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAllById(Iterable<? extends ID> ids) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteById(ID id) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean existsById(ID id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Iterable<T> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<T> findAllById(Iterable<ID> ids) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<T> findById(ID id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public <S extends T> S save(S entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends T> long count(Example<S> example) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public <S extends T> boolean exists(Example<S> example) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public <S extends T> Iterable<S> findAll(Example<S> example) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends T> Iterable<S> findAll(Example<S> example, Sort sort) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends T, R> R findBy(
      Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends T> Optional<S> findOne(Example<S> example) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }
}
