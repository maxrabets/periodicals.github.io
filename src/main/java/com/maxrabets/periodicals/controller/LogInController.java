package com.maxrabets.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxrabets.periodicals.entity.User;
import com.maxrabets.periodicals.service.UserService;

@Controller
public class LogInController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
    String logIn(@RequestParam String email, @RequestParam String password) {
    	if(userService.findByEmail(email).equals(null)) {
    		return "Wrong email or password";
    	}
    	User user = userService.findByEmail(email);
    	if(user.getPassword() != password) {
    		return "Wrong email or password";
    	}
    	
        return email;
    }
}
