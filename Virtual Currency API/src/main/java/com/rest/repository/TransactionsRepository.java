package com.rest.repository;

import com.rest.model.Transactions;
import com.rest.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Muhammad Atta
 *
 */

public interface TransactionsRepository extends CrudRepository<Transactions, Long>{
    List<Transactions> findByFrom(User from, Pageable pageable);
    List<Transactions> findByTo(User to, Pageable pageable);
    @Query("select t from Transactions t where t.from = :user or t.to = :user")
    List<Transactions> findByFromOrTo(@Param("user") User user, Pageable pageable);

    @Query("select t from Transactions t where t.from = :user or t.to = :user")
    List<Transactions> getOneTransactions(@Param("user") User user, Pageable pageable);

    public List<Transactions> findById(User user, Pageable pageable);

    @Query("select t.amount, t.to from Transactions t where t.from = :id")
    List<Transactions> getAllTransactionsByUser(@Param("id")long id);


    @Query(value = "SELECT  t.id, t.amount, t.to_id, t.from_id,t.time FROM   transactions t  where  t.from_id = :from", nativeQuery = true)
    public List<Transactions> findByFrom(String from);

    @Query(value = "SELECT  t.id, t.amount, t.from_id, t.to_id,t.time FROM   transactions t  where  t.to_id = :to", nativeQuery = true)
    public List<Transactions> findByTo(String to);



}