package com.maxrabets.periodicals.repository;

import com.maxrabets.periodicals.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAllByName(String name);
     
    User findByEmail(String email);
    
//    @Modifying
//    @Query("UPDATE Users u SET u.name = :name WHERE u.email = :email")
//    int updateNameByEmail(@Param("email") String email, @Param("name") String name);
}