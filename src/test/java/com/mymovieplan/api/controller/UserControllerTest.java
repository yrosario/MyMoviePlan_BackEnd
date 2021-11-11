package com.mymovieplan.api.controller;

//import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;




@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void addUserTest() throws Exception {
		
		String input = "{\r\n"
				+ "    \"fName\": \"tommy\",\r\n"
				+ "    \"lName\": \"Joesipe\",\r\n"
				+ "    \"address\": \"56 Washington St\",\r\n"
				+ "    \"city\": \"LA\",\r\n"
				+ "    \"email\": \"tjoesipe@gmail.com\",\r\n"
				+ "    \"birthday\": \"1983-11-01\",\r\n"
				+ "    \"password\": \"password\",\r\n"
				+ "    \"role\": \"admin\",\r\n"
				+ "    \"active\": false\r\n"
				+ "}";
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(input);
		

		mockMvc.perform(request)
				.andExpect(status().isCreated())
				//.andExpect(jsonPath("$.*", Matchers.hasSize(9)))
				.andReturn();
		
	}
	
	@Test
	public void getUserTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/user/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(""
						+ "{id: 1,\n"
						+ "payments: [ ],\n"
						+ "purchases: [ ],\n"
						+ "cart: null,\n"
						+ "fName: \"Joe\",\n"
						+ "lName: \"Burro\",\n"
						+ "address: \"1144 Harrison Ave\",\n"
						+ "city: \"Boston\",\n"
						+ "email: \"jburro@gmail.com\",\n"
						+ "birthday: \"1983-11-01\",\n"
						+ "password: \"password\",\n"
						+ "role: \"admin\",\n"
						+ "active: true}"))
				.andReturn();
		
		//assertEquals("hello world", result.getResponse().getContentAsString());
		
	}
	
	
	@Test
	public void getEmtpyUserTest() throws Exception{
		
		long id = 9999999999L;
		RequestBuilder request = MockMvcRequestBuilders
				.get("/user/"+id)
				.accept(MediaType.APPLICATION_JSON);
		
		String expected = "{\r\n"
				+ "    \"message\": \"id: -" + id + "\",\r\n"
				+ "    \"details\": \"uri=/user/"+id+"\"\r\n"
				+ "}";
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isNotFound())
				.andExpect(content().json(expected))
				.andReturn();
		
		
	}

}
