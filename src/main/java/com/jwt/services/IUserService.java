package com.jwt.services;

import java.util.List;


import com.jwt.exception.UserAlreadyExistsException;
import com.jwt.exception.UserNotFoundException;
import com.jwt.model.User;

public interface IUserService {

	public boolean validateUserService(String username, String password) throws UserNotFoundException;
	
	public User getUserByName(String name) throws UserNotFoundException;
	
	public List<User> getAllUsers();
	
	public User saveUser(User user) throws UserAlreadyExistsException;
	
}
