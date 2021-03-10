package com.rest.validators;

import com.rest.exceptions.InvalidFieldsException;
import com.rest.exceptions.UnauthorizedException;
import com.rest.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.rest.validators.ValidatorUtil.isVaildString;

/**
 *
 * @author Muhammad Atta
 *
 */

@Component
public class TokenValidator implements Validator<String> {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Override
	public void validate(String model) throws Exception {
		Validator.super.validate(model);
		jwtTokenUtil.doValidateToken(model);
	}
	
	@Override
	public void validateRequiredFields(String model) throws Exception {
		if (model == null) throw new UnauthorizedException();
	}

	@Override
	public void validateFieldsValues(String model) throws Exception {
		if (!isVaildString(model)) throw new InvalidFieldsException();
	}

	@Override
	public void validateUniqueFields(String model) throws Exception {}

}
