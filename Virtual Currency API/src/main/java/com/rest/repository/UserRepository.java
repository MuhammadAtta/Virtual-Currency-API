package com.rest.repository;

import com.rest.model.Transactions;
import com.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Muhammad Atta
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);



    Transactions findTransactionsById(int id);
  //  @Query("SELECT * FROM transactions where from_id =?1")
   // @Query("transactions where id=?1 and from_id =?2")
  //  Transactions findTransactionsByTransactionsIdUsingUserId(int from_id);


}
