package com.rest.security;

import com.rest.model.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Muhammad Atta
 *
 */

public class JwtUser implements Serializable {

	private static final long serialVersionUID = 3915802120609967850L;

	private String firstName;

	private String lastName;

	private String email;

	private List<Phone> phones;

	private Date created_at;

	private Date last_login;

	public JwtUser() {
	}

	public JwtUser(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.phones = user.getPhones();
		this.created_at = user.getCreated_at();
		this.last_login = user.getLast_login();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

}