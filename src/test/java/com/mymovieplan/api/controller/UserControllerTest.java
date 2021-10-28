package com.mymovieplan.api.controller;

//import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getUser_test() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/get-user/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("id: 1,\n"
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
						+ "active: true"))
				.andReturn();
		
		//assertEquals("hello world", result.getResponse().getContentAsString());
	}

}
