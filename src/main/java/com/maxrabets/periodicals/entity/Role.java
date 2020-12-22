package com.maxrabets.periodicals.entity;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
    private Integer roleId;
   
	@Column(name = "name")
    private String name;
	
	public Integer getId() {
        return roleId;
    }
 
    public void setId(Integer id) {
        this.roleId = id;
    }
    
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}
