package com.springframework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.dto.UserDao;
import com.springframework.model.UserEntity;
import com.springframework.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public UserEntity getUserById(Integer id) {
		userDao.getUserById(id);
		return null;
	}

	@Override
	public UserEntity getUserByName(String name) {
		userDao.getUserByName(name);
		return null;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userDao.getAllUsers();
	}

}
