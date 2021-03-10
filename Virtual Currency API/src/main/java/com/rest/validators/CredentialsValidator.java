package com.rest.validators;

import com.rest.exceptions.InvalidCredentialsException;
import com.rest.exceptions.MissingFieldsException;
import com.rest.model.User;
import com.rest.repository.UserRepository;
import com.rest.security.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.rest.validators.ValidatorUtil.isVaildString;
import static com.rest.validators.ValidatorUtil.isValidPassword;

/**
 *
 * @author Muhammad Atta
 *
 */

@Component
public class CredentialsValidator implements Validator<Credentials> {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void validate(Credentials model) throws Exception {
		Validator.super.validate(model);
		this.validateRegisteredFields(model);
	}

	@Override
	public void validateRequiredFields(Credentials model) throws Exception {

		if (!isVaildString(model.getEmail())) throw new MissingFieldsException();
		
		if (!isVaildString(model.getPassword())) throw new MissingFieldsException();
	}

	@Override
	public void validateFieldsValues(Credentials model) throws Exception {}

	@Override
	public void validateUniqueFields(Credentials model) throws Exception {}
	
	public void validateRegisteredFields(Credentials model) throws Exception {
		
		User user = userRepository.findByEmail(model.getEmail());
		
		if (user == null) throw new InvalidCredentialsException();
		
		if (!isValidPassword(model.getPassword(), user.getPassword())) throw new InvalidCredentialsException(); 
	}

}
