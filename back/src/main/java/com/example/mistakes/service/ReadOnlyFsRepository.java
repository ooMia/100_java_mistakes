package com.example.mistakes.service;

import com.example.mistakes.base.type.FsMeta;
import com.example.mistakes.base.type.Identifiable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 * ReadOnlyFsRepository
 *
 * <p>Repository interface for read-only operations
 *
 * @param <T> Entity
 * @see org.springframework.data.repository.Repository
 */
@Repository
class ReadOnlyFsRepository<T extends Identifiable<ID> & FsMeta, ID>
    implements CrudRepository<T, ID>, QueryByExampleExecutor<T> {

  Map<ID, T> data = new HashMap<>();

  @Override
  public @NonNull <S extends T> S save(@NonNull S entity) {
    data.put(entity.getId(), entity);
    return entity;
  }

  @Override
  public @NonNull Optional<T> findById(@NonNull ID id) {
    return Optional.ofNullable(data.get(id));
  }

  @Override
  public @NonNull Iterable<T> findAll() {
    return data.values();
  }

  @Override
  public <S extends T> Optional<S> findOne(Example<S> example) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findOne'");
  }

  @Override
  public <S extends T> Iterable<S> findAll(Example<S> example) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public <S extends T> Iterable<S> findAll(Example<S> example, Sort sort) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public <S extends T> long count(Example<S> example) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'count'");
  }

  @Override
  public <S extends T> boolean exists(Example<S> example) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'exists'");
  }

  @Override
  public <S extends T, R> R findBy(
      Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findBy'");
  }

  @Override
  public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
  }

  @Override
  public boolean existsById(ID id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'existsById'");
  }

  @Override
  public Iterable<T> findAllById(Iterable<ID> ids) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'count'");
  }

  @Override
  public void deleteById(ID id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public void delete(T entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public void deleteAllById(Iterable<? extends ID> ids) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
  }

  @Override
  public void deleteAll(Iterable<? extends T> entities) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
  }

  @Override
  public void deleteAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
  }
}
