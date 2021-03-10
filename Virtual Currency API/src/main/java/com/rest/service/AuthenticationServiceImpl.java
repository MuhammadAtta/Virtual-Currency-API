package com.rest.service;

import com.rest.model.User;
import com.rest.repository.UserRepository;
import com.rest.security.*;
import com.rest.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author Muhammad Atta
 *
 */

@Service
public class AuthenticationServiceImpl  implements AuthenticationService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	CredentialsValidator credentialsValidator;
	
	@Override
	public Token generateToken(User user) {
		
		String token = jwtTokenUtil.doGenerateToken(new JwtUser(user));
		
		// Get login date from the token which was just generated
		Date userLastLogin = jwtTokenUtil.getUserFromToken(token).getLast_login();
		// Then update user last login date
		user.setLast_login(userLastLogin);
		userRepository.save(user);
		
		return new Token(token);
	}

	@Override
	public Token authenticate(Credentials credentials) throws Exception {
		
		credentialsValidator.validate(credentials);
		
		User user = userRepository.findByEmail(credentials.getEmail());
		
		return this.generateToken(user);
	}

}
