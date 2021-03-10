package com.rest.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Muhammad Atta
 *
 */
public class AuthenticationRestControllerTest extends RestControllerTest {

	final String SIGNUP = "/signup";

	final String SIGNIN = "/login";

	private JSONObject user;

	private JSONObject phone;

	private JSONObject credentials;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Before
	public void setup() {
		user = new JSONObject();
		phone = new JSONObject();
		credentials = new JSONObject();
	}

	@Test
	public void signup_signedUpSuccessfully_shouldReturnToken() throws Exception {
		// given
		phone.put("number", 111111111).put("area_code", 11).put("country_code", "+20");

		user.put("firstName", "Muhammad").put("lastName", "Atta").put("email", "muhammad.abdelmageed@email.com").put("password", "123456")
				.put("phones", new JSONArray().put(phone));

		// when
		JSONObject token = doPostAndReturnJson(SIGNUP, user.toString());

		// then
		error.checkThat(token, notNullValue());
		error.checkThat(token.has("token"), equalTo(true));
	}

	@Test
	public void signup_emailAlreadyExists_shouldReturnErrorMessage() throws Exception {
		// given
		phone.put("number", 111111111).put("area_code", 11).put("country_code", "+20");

		user.put("firstName", "Muhammad").put("lastName", "Test").put("email", "muhammad.abdelmageed@email.com").put("password", "123456")
				.put("phones", new JSONArray().put(phone));

		doPostAndReturnString(SIGNUP, user.toString());

		JSONObject otherUser = new JSONObject().put("firstName", "Muhammad").put("lastName", "Atta")
				// same email as first user's
				.put("email", "muhammad.abdelmageed@email.com").put("password", "123456").put("phones", new JSONArray().put(phone));

		// when
		String result = doPostAndReturnString(SIGNUP, otherUser.toString());

		// then
		assertThat(result, containsString("E-mail already exists"));
	}

	@Test
	public void signup_invalidFields_shouldReturnErrorMessage() throws Exception {
		// given
		phone.put("number", 111111111).put("area_code", 11).put("country_code", "+20");

		user.put("firstName", "Muhammad").put("lastName", "Atta")
				// an invalid email
				.put("email", "invalid").put("password", "123456").put("phones", new JSONArray().put(phone));

		// when
		String result = doPostAndReturnString(SIGNUP, user.toString());

		// then
		assertThat(result, containsString("Invalid fields"));
	}

	@Test
	public void signup_missingFields_shouldReturnErrorMessage() throws Exception {
		// given
		phone.put("number", 111111111).put("area_code", 11).put("country_code", "+20");

		user
				// missing first name field
				.put("lastName", "Test").put("email", "muhammad.abdelmageed@email.com").put("password", "123456")
				.put("phones", new JSONArray().put(phone));

		// when
		String result = doPostAndReturnString(SIGNUP, user.toString());

		// then
		assertThat(result, containsString("Missing fields"));
	}

	@Test
	public void signin_signedInSuccessfully_shouldReturnToken() throws Exception {
		// given
		phone.put("number", 111111111).put("area_code", 11).put("country_code", "+20");

		user.put("firstName", "Muhammad").put("lastName", "Atta").put("email", "muhammad.abdelmageed@email.com").put("password", "123456")
				.put("phones", new JSONArray().put(phone));

		doPostAndReturnString(SIGNUP, user.toString());

		credentials.put("email", "muhammad.abdelmageed@email.com").put("password", "123456");

		// when
		JSONObject token = doPostAndReturnJson(SIGNIN, credentials.toString());

		// then
		error.checkThat(token, notNullValue());
		error.checkThat(token.has("token"), equalTo(true));
	}

	@Test
	public void signin_nonexistentEmailOrWrongPassword_shouldReturnErrorMessage() throws Exception {
		// given
		credentials
				// nonexistent email
				.put("email", "muhammad.abdelmageed@email.com").put("password", "123456");

		// when
		String result = doPostAndReturnString(SIGNIN, credentials.toString());

		// then
		assertThat(result, containsString("Invalid e-mail or password"));
	}

	@Test
	public void signin_missingFields_shouldReturnErrorMessage() throws Exception {
		// given
		credentials.put("email", "muhammad.abdelmageed@email.com");
		// missing password field

		// when
		String result = doPostAndReturnString(SIGNIN, credentials.toString());

		// then
		assertThat(result, containsString("Missing fields"));
	}

}
