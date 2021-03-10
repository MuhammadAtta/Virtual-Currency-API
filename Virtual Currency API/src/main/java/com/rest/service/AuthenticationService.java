package com.rest.service;

import com.rest.model.User;
import com.rest.security.Credentials;
import com.rest.security.Token;

/**
 *
 * @author Muhammad Atta
 *
 */

public interface AuthenticationService {


	public Token generateToken(User user);
	
	public Token authenticate(Credentials credentials) throws Exception;
}
