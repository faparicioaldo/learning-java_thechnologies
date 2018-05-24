package com.springframework.service;

import java.util.List;
import com.springframework.model.UserEntity;

public interface UserService {
	public UserEntity getUserById(Integer id);
	public UserEntity getUserByName(String name);
	public List<UserEntity> getAllUsers();		
}
