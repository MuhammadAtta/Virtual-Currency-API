package com.rest.rest;

import com.rest.security.JwtTokenUtil;
import com.rest.security.JwtUser;
import com.rest.service.AuthorizationService;
import com.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Muhammad Atta
 *
 */

@RestController
@RequestMapping(value = "/")
public class UserRestController {

	@Value("${jwt.header.authorization}")
	private String authorization;

	@Autowired
	AuthorizationService authorizationService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserService userService;

	@GetMapping("/me")
	private ResponseEntity<Object> me(HttpServletRequest request) throws Exception {

		String token = request.getHeader(authorization);

		authorizationService.authorize(token);

		JwtUser jwtUser = jwtTokenUtil.getUserFromToken(token);

		return ResponseEntity.ok().body(jwtUser);
	}




}
