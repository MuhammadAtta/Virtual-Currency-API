package com.rest.rest;

import com.rest.model.User;
import com.rest.repository.UserRepository;
import com.rest.security.Credentials;
import com.rest.security.Token;
import com.rest.service.AuthenticationService;
import com.rest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;

/**
 *
 * @author Muhammad Atta
 *
 */

@RestController
@RequestMapping(value = "/")
public class AuthenticationRestController {
	private static Logger logger = LoggerFactory.getLogger(TransferController.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	Validator validator;

	@PostMapping("/signup")
	private ResponseEntity<Object> signup(@RequestBody User user) throws Exception {

		userService.save(user);

		Token token = authenticationService.generateToken(user);

		return ResponseEntity.ok().body(token);
	}

	@PostMapping("/login")
	private ResponseEntity<Object> login(@RequestBody Credentials credentials) throws Exception {

		Token token = authenticationService.authenticate(credentials);

		return ResponseEntity.ok().body(token);
	}


}
