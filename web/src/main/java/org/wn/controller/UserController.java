package org.wn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wn.exception.UserNotFoundException;
import org.wn.model.User;
import org.wn.service.UserService;

@RestController
public class UserController {

	final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService service;
	
	@Autowired
	private void setUserService(UserService service){
		this.service = service;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(@RequestParam(name="page", defaultValue = "1") Integer pageIndex) {
		
		logger.debug("Get users from page {} from paging supported dataset", pageIndex);
		return service.list(pageIndex);	   
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable(value="id") Long userId) {
		
		logger.debug("Get user by id = {}", userId);
		User user = service.find(userId);
		return user;
	} 
	
	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE/*, consumes=MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json"*/ )
	public User createUser(@Valid @RequestBody final User user, BindingResult result) {
		
		logger.debug("Create user {} from paging supported dataset", user);
		
		if (result.hasErrors()) {
			logger.error("User is not valid and cannot be created. Throw exception..");
			throw new IllegalArgumentException("User details are not valid");
		}else{
			this.service.add(user);
			return user;
		}
    }
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@PathVariable(value="id") Long userId, @Valid @RequestBody User user) {
		
		logger.warn("Call to unsupported method..");
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable(value="id") Long userId) throws UserNotFoundException {
		
		logger.debug("Delete user with id = {}", userId);
		this.service.remove(userId);
	}
	
}
