package com.rest.service;

import com.rest.model.Transactions;
import com.rest.model.User;
import com.rest.repository.TransactionsRepository;
import com.rest.repository.UserRepository;
import com.rest.validators.UserValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Muhammad Atta
 *
 */

@Service
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();
	@Autowired
	private TransactionsRepository transactionsRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserValidator userValidator;

	private static List<User> users;

	static{
		users= populateDummyUsers();
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		for (User user : users) {

			users.add(user);

		}
		return users;
	}




	@Override
	public User save(User user) throws Exception {
		
		userValidator.validate(user);
		
		this.encryptPassword(user);
		
		return userRepository.save(user);
	}


	public User findByName(String name) {
		for(User user : users){
			if(user.getUserName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}




	//**************************************************************************************************
	// Transactions FUNCTION START HERE

	public Transactions getTransactionsOfUser( int id) {
		return (Transactions) userRepository.findTransactionsById(id);
	}









	//I should check for email's user, not for name.
	public boolean isUserExist(User user) {
		return findByName(user.getUserName())!=null;
	}


	private void encryptPassword(User user) {
		String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(encryptedPassword);
	}

	//@Scheduled(cron="0 0/10 * * * *")
	//@Scheduled(cron="0 * * * * *")
	  @Scheduled(cron="0 0/30 * * * *")

	public  void accrueScheduled() {
		for (User user: userRepository.findAll()) {
			user.setVirtualCurrency(calculateVirtualCurrency(user));
			update(user);
		}
	}

	void update(User user){
		userRepository.save(user);
	}

	BigDecimal calculateVirtualCurrency(User user) {
		System.out.println("db1 " +user.getVirtualCurrency());
		double accrue = 0.25;
		BigDecimal accrueVirtualCurrency = user.getVirtualCurrency();
		// Convert the double input to BigDecimal
		BigDecimal a = new BigDecimal(accrue);
        // BigDecimal object to store the result
		BigDecimal sum;
		// Using add() method
		sum = accrueVirtualCurrency.add(a);

		user.setVirtualCurrency(sum);
		System.out.println("a .... " + a);
		System.out.println("db2 " + user.getVirtualCurrency());
		System.out.println("sum .... " + sum);
		return sum;
	}



}
