package com.rest.validators;


import com.rest.exceptions.InvalidFieldsException;
import com.rest.exceptions.MissingFieldsException;
import com.rest.model.Phone;
import org.springframework.stereotype.Component;

import static com.rest.validators.ValidatorUtil.isVaildString;
import static com.rest.validators.ValidatorUtil.isValidPositiveInteger;

/**
 *
 * @author Muhammad Atta
 *
 */

@Component
public class PhoneValidator implements Validator<Phone> {
	
	@Override
	public void validateRequiredFields(Phone model) throws Exception {
		
		if (model.getNumber() == null) throw new MissingFieldsException();
		
		if (model.getArea_code() == null) throw new MissingFieldsException();
		
		if (model.getCountry_code() == null) throw new MissingFieldsException();
	}

	@Override
	public void validateFieldsValues(Phone model) throws Exception {
		
		if (!isValidPositiveInteger(model.getNumber())) throw new InvalidFieldsException();
		
		if (!isValidPositiveInteger(model.getArea_code())) throw new InvalidFieldsException();
		
		if (!isVaildString(model.getCountry_code())) throw new InvalidFieldsException();
	}

	@Override
	public void validateUniqueFields(Phone model) throws Exception {}

}
