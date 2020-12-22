package com.maxrabets.periodicals.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxrabets.periodicals.config.jwt.JwtProvider;
import com.maxrabets.periodicals.entity.Role;
import com.maxrabets.periodicals.entity.User;
import com.maxrabets.periodicals.responses.UserResponse;
import com.maxrabets.periodicals.service.UserService;

@Controller
public class AdminController {
	@Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
	
//    @GetMapping(value="/admin/private-area")
//    public String goToAdminPrivateArea(@CookieValue(value="token") Cookie cookieToken) {
//		if(cookieToken == null)
//    		return "redirect:login";
//    	else {
//    		String token = cookieToken.getValue();
//    		String login = jwtProvider.getLoginFromToken(token);
//    		Role role = userService.getRole(login);
//    		if(role.getName() == "ROLE_ADMIN")
//    			return "redirect:admin-private-area.html";
//    		else {
//    			return "redirect:../login";
//    		}
//    	}
//	}
    
    @GetMapping(value="/admin/get-users")
	@ResponseBody
    public List<UserResponse> getUsers(@CookieValue(value="token", defaultValue="") String cookieToken) {
    	if(cookieToken == null) {
    		System.err.println("null");
    		return null;
    	}	
		String token = cookieToken;
		if(token.isEmpty())
			return null;
		String login = jwtProvider.getLoginFromToken(token);
		User user = userService.findByEmail(login);
		if(user.getRoleId() != 1) {
			System.err.println("null");
			return null;
		}
		System.err.println("ok");
		List<User> users = userService.findAll();
		List<UserResponse> response = new ArrayList<UserResponse>();
		for(User u : users) {
			response.add(new UserResponse(u));
		}
		return response;
    }
}
