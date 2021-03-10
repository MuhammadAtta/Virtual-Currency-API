package com.rest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Muhammad Atta
 *
 */

@RestController
@RequestMapping(value = "/")
public class HomeRestController {

	@GetMapping
	public String sayHello() {
		return "Welcome to the Virtual Currency Application. API endpoint.";
	}
}
