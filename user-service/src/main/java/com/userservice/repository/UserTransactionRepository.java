package com.userservice.repository;

import com.userservice.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {
}
