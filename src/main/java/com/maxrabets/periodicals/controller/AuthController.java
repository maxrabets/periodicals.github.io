package com.maxrabets.periodicals.controller;

import java.net.http.HttpHeaders;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.maxrabets.periodicals.config.jwt.JwtProvider;
import com.maxrabets.periodicals.entity.Role;
import com.maxrabets.periodicals.entity.User;
import com.maxrabets.periodicals.requests.AuthRequest;
import com.maxrabets.periodicals.requests.RegistrationRequest;
import com.maxrabets.periodicals.service.UserService;
import com.maxrabets.periodicals.Constants;

//@RestController
@Controller
public class AuthController {
	
	public static final String URL = "http://localhost:8080";
	
	private boolean emailVerification = true; 
	
	@Autowired
	private JavaMailSender emailSender;
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Value("${error.message}")
    private String errorMessage;
    
        
    @GetMapping(value="private-area")
    public String goToPrivateArea(@CookieValue(value="token", defaultValue = "") String cookieToken) {
    	if(cookieToken == null)
    		return "redirect:login.html";
    	else {
    		String token = cookieToken;
    		if(token.isEmpty())
    			return "redirect:login.html";
    		String login = jwtProvider.getLoginFromToken(token);
    		Role role = userService.getRole(login);
    		if(role.getName() == "ROLE_ADMIN")
    			return "redirect:/admin/admin-private-area.html";
    			//return "redirect:/admin/private-area";
    		else if (role.getName() == "ROLE_CLIENT"){
    			return "redirect:/client/client-private-area.html";
    		}
    		else
    			return "redirect:login.html";
    	}
    }
    
    @PostMapping(value="/register"/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public ResponseEntity<?> registerUser(/*@RequestBody*/ @Valid RegistrationRequest registrationRequest, HttpServletResponse response) {
        if(userService.findByEmail(registrationRequest.getEmail()) == null) {
        	User user = new User();
	        user.setPassword(registrationRequest.getPassword());
	        user.setEmail(registrationRequest.getEmail());
	        user.setName(registrationRequest.getName());
	        user.setSurname(registrationRequest.getSurname());
	        user.setAddress(registrationRequest.getAddress());	        
        	if(!emailVerification) {
        		userService.saveClient(user);
		        String token = jwtProvider.generateToken(user.getEmail());
		        Cookie cookie = new Cookie("token", token);
		        response.addCookie(cookie);
		        return ResponseEntity.ok(null);
        	}
        	else {
        		try {
	        		SimpleMailMessage message = new SimpleMailMessage();
	        		System.err.println("setto");
	        		message.setTo(user.getEmail());
	        		message.setSubject("Periodicals: registration");
	        		message.setText("Hi " + user.getName() + " " + user.getSurname() + "!\r\n" 
	        				+ "Your registration on " + URL + " is finished!");
	        		System.err.println("sending...");
	        		new Thread(() -> {
	        				emailSender.send(message);
	        		}).start();
	        		System.err.println("sent");	   
	        		userService.saveClient(user);
			        String token = jwtProvider.generateToken(user.getEmail());
			        Cookie cookie = new Cookie("token", token);
			        response.addCookie(cookie);
	        		//ResponseEntity.status(HttpStatus.SEE_OTHER).header(HttpHeaders., "/registration-finish");
	        		return (new ResponseEntity<>(HttpStatus.ACCEPTED));
        		} catch (Exception e) {
        			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        		}
        	}
        }
        return new ResponseEntity<>("Account wasn't created. There is already account registered with this email.", HttpStatus.CONFLICT);
    }

    @PostMapping("/auth")
    public /*AuthResponse*/ResponseEntity<?> auth(/*@RequestBody*/ AuthRequest request, HttpServletResponse response, Model model) {
        User user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if(user!=null) {
	        String token = jwtProvider.generateToken(user.getEmail());
	        Cookie cookie = new Cookie("token", token);
	        response.addCookie(cookie);
	        return ResponseEntity.ok(null);
	        //return "redirect:";
        }
        return new ResponseEntity<>("Wrong email or password.", HttpStatus.BAD_REQUEST);
        
        //System.err.println("error");
        //model.addAttribute("errorMessage", errorMessage);
        //return "redirect:login.html";
        //return new AuthResponse(token);
    }
}