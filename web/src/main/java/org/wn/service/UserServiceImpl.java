package org.wn.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wn.controller.UserController;
import org.wn.exception.UserAlreadyExistsException;
import org.wn.exception.UserNotFoundException;
import org.wn.model.User;
import org.wn.repository.UserRepository;
import org.wn.util.ResponseUtil;

@Service
@Transactional 
public class UserServiceImpl implements UserService {
	
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository repository;
		
	@Override
	public List<User> list(){
		return repository.findAll();
	}
	
	@Override
	public List<User> list(Integer pageIndex){
		if(pageIndex == null){
			logger.warn("page index is Null. Retrieve all the users.");
			return list();
		}
		Pageable pageSpecification = new PageRequest(pageIndex, ResponseUtil.NUMBER_OF_PERSONS_PER_PAGE); 
		Page<User> requestedPage = repository.findAll(pageSpecification);
		return requestedPage.getContent();
	}
	
	@Override
	public User find(Long userId) throws UserNotFoundException{
		if(userId == null){
			throw new IllegalArgumentException("UserId should not be null");
		}
		Optional<User> user = Optional.ofNullable(repository.findOne(userId));
		return user.orElseThrow(UserNotFoundException::new);
	}
		
	@Override
	public User add(User user) throws UserAlreadyExistsException{
		
		if(user == null){
			throw new IllegalArgumentException("User cannot be saved. Null object passed.");
		}
		if(repository.exists(user.getId())){
			throw new UserAlreadyExistsException("User already exists!");
		}
		return repository.save(user);
	}
	
	@Override
	public User update(User user, Long userId) throws UserNotFoundException{
		
		if(repository.exists(userId)){
			throw new UserNotFoundException("User cannot be found in order to be updated!");
		}
		return repository.save(user);
	}
	
	@Override
	public void remove(Long userId) throws UserNotFoundException{
		
		if(repository.exists(userId)){
			throw new UserNotFoundException("User cannot be found in order to be deleted!");
		}
		repository.delete(userId);
	}
	
	//Predicate<User> userExists (){ return u -> repository.exists(u.getId()); }
}
