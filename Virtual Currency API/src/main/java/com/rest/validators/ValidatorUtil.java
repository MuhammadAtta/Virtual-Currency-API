package com.rest.validators;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 *
 * @author Muhammad Atta
 *
 */

public class ValidatorUtil {
	
	public static boolean isVaildString(String string) {
		return (string != null && string.trim().length() > 0);
	}
	
	public static boolean isValidPositiveInteger(Integer integer) {
		return (integer != null && integer > 0);
	}
	
	public static boolean isValidList(List<?> list) {
		return (list != null && !list.isEmpty());
	}
	
	public static boolean isValidEmail(String email) {
		
		if (email == null) return false;
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
		return Pattern.compile(emailRegex, CASE_INSENSITIVE).matcher(email).matches(); 
	}

	public static boolean isValidPassword(String informedPassword, String registeredPassword) {
		return BCrypt.checkpw(informedPassword, registeredPassword);
	}

}
