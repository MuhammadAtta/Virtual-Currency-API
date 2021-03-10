package com.rest.controllers;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Muhammad Atta
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	ObjectMapper mapper;
	
	public String doPostAndReturnString(String url, String content) throws Exception {
		return mvc.perform(post(url).contentType(APPLICATION_JSON).content(content.toString())).andReturn().getResponse().getContentAsString();
	}
	
	public JSONObject doPostAndReturnJson(String url, String content) throws Exception {
		return new JSONObject(doPostAndReturnString(url, content));
	}
	
	public String doGetAndReturnString(String url, HttpHeaders headers) throws Exception {
		return mvc.perform(get(url).headers(headers)).andReturn().getResponse().getContentAsString();
	}
	
	public JSONObject doGetAndReturnJson(String url, HttpHeaders httpHeaders) throws Exception {
		return new JSONObject(doGetAndReturnString(url, httpHeaders));
	}
	
	public String toJson(Object object) throws JsonProcessingException {
		return new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(object);
	}

}
