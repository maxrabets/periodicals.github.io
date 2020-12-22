package com.maxrabets.periodicals.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxrabets.periodicals.config.jwt.JwtProvider;
import com.maxrabets.periodicals.entity.Role;
import com.maxrabets.periodicals.entity.User;
import com.maxrabets.periodicals.responses.UserResponse;
import com.maxrabets.periodicals.service.UserService;

@Controller
public class ClientController {
	@Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
	
//	@GetMapping(value="/client/private-area")
//    public String goToClientPrivateArea(@CookieValue(value="token") Cookie cookieToken) {
//		if(cookieToken == null)
//    		return "redirect:login";
//    	else {
//    		String token = cookieToken.getValue();
//    		String login = jwtProvider.getLoginFromToken(token);
//    		Role role = userService.getRole(login);
//    		if(role.getName() == "ROLE_CLIENT")
//    			return "redirect:client-private-area.html";
//    		
//    	}
//		return "redirect:../login";
//	}
    
    @GetMapping(value="/client/client-private-data")
	@ResponseBody
    public UserResponse goToClientPrivateArea(@CookieValue(value="token", defaultValue="") String token) {
    	if(token == null) {
    		System.err.println("null");
    		return null;
    	}    	
		//String token = cookieToken.getValue();
		if(token.isEmpty())
			return null;
		String login = jwtProvider.getLoginFromToken(token);
		User user = userService.findByEmail(login);
		return new UserResponse(user);
    }
    
    @PatchMapping(value="/client/change-name")
    public ResponseEntity<?> changeName(@CookieValue(value="token", defaultValue="") String token, @RequestBody String name){
    	if(token == null || token.isEmpty() || name == null || name.isBlank()) {
    		System.err.println("null");
    		return null;
    	}
    	String login = jwtProvider.getLoginFromToken(token);
    	userService.updateNameByEmail(login, name);
    	return ResponseEntity.ok(null);
    }
    
    @PatchMapping(value="/client/change-surname")
    public ResponseEntity<?> changeSurname(@CookieValue(value="token", defaultValue="") String token, @RequestBody String surname){
    	if(token == null || token.isEmpty() || surname == null || surname.isBlank()) {
    		System.err.println("null");
    		return null;
    	}
    	String login = jwtProvider.getLoginFromToken(token);
    	userService.updateSurnameByEmail(login, surname);
    	return ResponseEntity.ok(null);
    }
    
    @PatchMapping(value="/client/change-address")
    public ResponseEntity<?> changeAddress(@CookieValue(value="token", defaultValue="") String token, @RequestBody String address){
    	if(token == null || token.isEmpty() || address == null || address.isBlank()) {
    		System.err.println("null");
    		return null;
    	}
    	String login = jwtProvider.getLoginFromToken(token);
    	userService.updateAddressByEmail(login, address);
    	return ResponseEntity.ok(null);
    }
    
    @DeleteMapping(value="/client/client-delete")
    public ResponseEntity<?> changeAddress(@CookieValue(value="token", defaultValue="") String token){
    	if(token == null || token.isEmpty()) {
    		System.err.println("null");
    		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    	}
    	String login = jwtProvider.getLoginFromToken(token);
    	if(userService.deleteByEmail(login))
    		return ResponseEntity.ok(null);
    	else
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
//	@GetMapping(value="/client/client-private-area.html")
//	//@ResponseBody
//    public String goToClientPrivateArea(@CookieValue(value="token") Cookie cookieToken) {
//		if(cookieToken == null)
//    		return "redirect:login";
//    	else {
//    		String token = cookieToken.getValue();
//    		String login = jwtProvider.getLoginFromToken(token);
//    		Role role = userService.getRole(login);
//    		if(role.getName() == "ROLE_CLIENT")
//    			return "redirect:client-private-area.html";
//    	}
//		return "redirect:login";
//	}
}
