package com.maxrabets.periodicals.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxrabets.periodicals.entity.User;
import com.maxrabets.periodicals.service.UserService;

@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;
	

    @PostMapping("/registration")
    String registration(@RequestParam String name,@RequestParam String surname,
    		@RequestParam String email, @RequestParam String password,
    		@RequestParam String address) {
    	if(!userService.findByEmail(email).equals(null)) {
    		return "Account with this email already exists";
    	}
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setRoleId(1);
        userService.saveClient(user);
        return email;
    }
}