package com.rest.validators;

import com.rest.exceptions.EmailAlreadyExistsException;
import com.rest.exceptions.InvalidFieldsException;
import com.rest.exceptions.MissingFieldsException;
import com.rest.model.Phone;
import com.rest.model.User;
import com.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.rest.validators.ValidatorUtil.*;

/**
 *
 * @author Muhammad Atta
 *
 */

@Component
public class UserValidator implements Validator<User> {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PhoneValidator phoneValidator;

	@Override
	public void validateRequiredFields(User model) throws Exception {
		
		if (model.getFirstName() == null) throw new MissingFieldsException();
		
		if (model.getLastName() == null) throw new MissingFieldsException();
		
		if (model.getEmail() == null) throw new MissingFieldsException();
		
		if (model.getPassword() == null) throw new MissingFieldsException();		

		if (model.getPhones() == null) throw new MissingFieldsException();
	}

	@Override
	public void validateFieldsValues(User model) throws Exception {
		
		if (!isVaildString(model.getFirstName())) throw new InvalidFieldsException();
		
		if (!isVaildString(model.getLastName())) throw new InvalidFieldsException();
		
		if (!isValidEmail(model.getEmail())) throw new InvalidFieldsException();
		
		if (!isVaildString(model.getPassword())) throw new InvalidFieldsException();

		if (!isValidList(model.getPhones())) throw new InvalidFieldsException();
		
		for (Phone phone : model.getPhones()) phoneValidator.validate(phone);
	}
	
	@Override
	public void validateUniqueFields(User model) throws Exception {
		if (userRepository.findByEmail(model.getEmail()) != null) throw new EmailAlreadyExistsException();
	}

}
