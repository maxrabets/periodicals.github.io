package com.maxrabets.periodicals.entity;


import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;



@Entity
@DynamicUpdate
@Table(name="users")
public class User {
	   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
    private Integer userId;
   
	@Column(name = "name")
    private String name;
    
	@Column(name = "surname")
    private String surname;
   
	@Column(name = "email")
    private String email;
   
	@Column(name = "password")
    private String password;
    
	@Column(name = "address")
    private String address;
    
	@Column(name = "ROLE_ID")
    private Integer roleId;
	
	//private Role role;

	public Integer getId() {
        return userId;
    }
 
    public void setId(Integer id) {
        this.userId = id;
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
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
 
    public void setRoleId(Integer id) {
    	//if
        this.roleId = id;
    }
    
    public Role getRole() {
    	// костыль
    	Role role = new Role();
    	role.setId(roleId);
		if(roleId == 1) {
			role.setName("ROLE_ADMIN");
		}
		if(roleId == 2) {
			role.setName("ROLE_CLIENT");
		}
		return role;
	}

	public void setRole(Role role) {
		this.roleId = role.getId();
	}
}
