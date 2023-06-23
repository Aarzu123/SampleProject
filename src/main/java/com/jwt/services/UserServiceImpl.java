package com.jwt.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.exception.UserAlreadyExistsException;
import com.jwt.exception.UserNotFoundException;
import com.jwt.model.User;
import com.jwt.repository.IUserRespository;

@Service
public class UserServiceImpl implements IUserService 
{
	@Autowired
	private IUserRespository userRepository;
	
	
	@Override
	public boolean validateUserService(String username, String password) throws UserNotFoundException {
		
		User user = userRepository.validateUser(username, password);
		
		System.out.println("user" + user);
		
		if(user != null)
			return true;
		else
			return false;
	}

	@Override
	public User getUserByName(String name) throws UserNotFoundException {
		return userRepository.fidByUName(name);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> optional = this.userRepository.findById(user.getUserId());
		if(optional.isPresent()) {
			throw new UserAlreadyExistsException();
		}
		
			User createdUser = userRepository.save(user);
		
		return createdUser;
	}
}
