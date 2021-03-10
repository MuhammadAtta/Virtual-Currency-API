package com.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * 
 * @author Muhammad Atta
 *
 */

@Entity
@Table(name = "PHONE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "number", "area_code", "country_code" }) })
public class Phone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5310319068347042516L;

	@Id
	private Integer number;

	@Id
	private Integer area_code;

	@Id
	private String country_code;

	public Phone() {
		super();
	}

	public Phone(Integer number, Integer area_code, String country_code) {
		super();
		this.number = number;
		this.area_code = area_code;
		this.country_code = country_code;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getArea_code() {
		return area_code;
	}

	public void setArea_code(Integer area_code) {
		this.area_code = area_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

}
