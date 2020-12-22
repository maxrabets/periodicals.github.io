package com.maxrabets.periodicals.responses;

import com.maxrabets.periodicals.entity.Role;
import com.maxrabets.periodicals.entity.User;

public class UserResponse {
    private Integer id;
    
    private String name;
    
    private String surname;
   
    private String email;
    
    private String address;
    
    private Role role;
    
    public UserResponse(User user) {
    	id = user.getId();
    	name = user.getName();
    	surname = user.getSurname();
    	address = user.getAddress();
    	email = user.getEmail();
    	role = user.getRole();
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
