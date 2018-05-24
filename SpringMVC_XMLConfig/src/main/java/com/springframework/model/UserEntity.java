package com.springframework.model;

public class UserEntity {

	private Integer id;
	private String name;
	private String firstName;
	private String lastName;
	
	/**
	 * @param iduser
	 * @param name
	 * @param firstName
	 * @param lastName
	 * 
	 * */
	public UserEntity(Integer id, String name, String firstName, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}
