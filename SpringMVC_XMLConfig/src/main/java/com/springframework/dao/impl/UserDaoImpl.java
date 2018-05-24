package com.springframework.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springframework.dto.UserDao;
import com.springframework.model.UserEntity;

@Repository
public class UserDaoImpl implements UserDao{

	@Override
	public UserEntity getUserById(Integer id) {
		
		return new UserEntity(1,"Juan","Perez","Gomez");
	}

	@Override
	public UserEntity getUserByName(String name) {
		
		return new UserEntity(1,"Juan","Perez","Gomez");
	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> users = new ArrayList<UserEntity>();
		
		users.add(new UserEntity(1,"Juan","Perez","Gomez"));
		users.add(new UserEntity(1,"Maria","Perez","Gomez"));
		users.add(new UserEntity(1,"Hector","Perez","Gomez"));

		return users;
	}
}
