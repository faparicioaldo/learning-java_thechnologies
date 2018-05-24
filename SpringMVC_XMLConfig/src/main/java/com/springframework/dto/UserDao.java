package com.springframework.dto;

import java.util.List;

import com.springframework.model.UserEntity;

public interface UserDao {
	public UserEntity getUserById(Integer id);
	public UserEntity getUserByName(String name);
	public List<UserEntity> getAllUsers();
}
