package com.rest.service;

import com.rest.validators.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad Atta
 *
 */

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	TokenValidator tokenValidator;
	
	@Override
	public void authorize(String token) throws Exception {
		tokenValidator.validate(token);
	}

}
