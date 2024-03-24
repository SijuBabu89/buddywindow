package com.buddywindow.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.repository.IUserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository repository;
	
	@Override
	public User getUserById(Long id) {
		Optional<User> user= repository.findById("");
		if(user.isPresent()) {
			return user.get();
		} else {
			throw new RuntimeException("User not found");
		}
	}
	
	@Override
	public User createUser(User user) {
		user = repository.save(user);
		return user;
	}

}
