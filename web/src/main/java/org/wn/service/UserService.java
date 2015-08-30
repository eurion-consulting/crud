package org.wn.service;

import java.util.List;

import org.wn.exception.UserAlreadyExistsException;
import org.wn.exception.UserNotFoundException;
import org.wn.model.User;

public interface UserService {

	public abstract List<User> list();

	public abstract List<User> list(Integer pageIndex);

	public abstract User find(Long userId) throws UserNotFoundException;

	public abstract User add(User user) throws UserAlreadyExistsException;

	public abstract User update(User user, Long userId)
			throws UserNotFoundException;

	public abstract void remove(Long userId) throws UserNotFoundException;

}