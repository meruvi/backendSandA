package com.youtube.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube.demo.model.User;
import com.youtube.demo.service.UserService;
import com.youtube.demo.util.RestResponce;

@RestController
public class UserController {

	@Autowired
	protected UserService userService;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public RestResponce saveOrUpdate(@RequestBody String userJson) 
			throws JsonParseException, JsonMappingException, IOException {
		
		this.mapper = new ObjectMapper();
		
		User user = this.mapper.readValue(userJson, User.class);
		
		if(!this.validate(user)) {
			return new RestResponce(HttpStatus.NOT_ACCEPTABLE.value(), 
					"Los campos oblogatorios no estan diligenciados");
		}
		
		this.userService.save(user);
		
		return new RestResponce(HttpStatus.OK.value(), "Operación exitosa");
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getUsers() {
		return this.userService.findAll();
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(@RequestBody String userJson) 
			throws Exception {
		
		this.mapper = new ObjectMapper();
		User user = this.mapper.readValue(userJson, User.class);
		
		if(user.getId() == null) {
			throw new Exception("El id esta nulo");
		}
		
		this.userService.deleteUser(user.getId());
		
	}
	
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public void deleteUserById(@PathVariable("userId") Long userId){
		
		Optional<User> user= this.userService.findByUserId(userId);
		
		if(user != null) {
			this.userService.deleteUser(userId);
		}
	
	}
	
	private boolean validate(User user) {
		boolean isValid = true;
		
		if(user.getFirstName() == null || user.getFirstName() == "") {
			isValid = false;
		}
		if(user.getFirstSurname() == null || user.getFirstSurname() == "") {
			isValid = false;
		}
		if(user.getAddress() == null || user.getAddress() == "") {
			isValid = false;
		}
		
		return isValid;
	}
}
