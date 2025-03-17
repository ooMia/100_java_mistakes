package com.example.mistakes.api.questions.service;

import org.springframework.data.repository.CrudRepository;
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
interface ReadOnlyFsRepository<T, ID>
    extends CrudRepository<T, ID>, QueryByExampleExecutor<T> {}
