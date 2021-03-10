package com.rest.service;

import com.rest.model.User;

/**
 *
 * @author Muhammad Atta
 *
 */

public interface UserService {
	
	public User save(User user) throws Exception;

	//Optional<User> findById(List<Long> from);
	boolean isUserExist(User user);

	User findByName(String name);

	void saveUser(User user);


}
