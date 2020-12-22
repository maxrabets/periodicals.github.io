package com.maxrabets.periodicals.service;


import com.maxrabets.periodicals.entity.Role;
import com.maxrabets.periodicals.entity.User;
import com.maxrabets.periodicals.repository.RoleRepository;
import com.maxrabets.periodicals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class UserService {
 
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private RoleRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public boolean deleteByEmail(String email) {
    	User user = findByEmail(email);
    	if(user==null)
    		return false;
    	userRepository.delete(user);
    	return true;
    }
    
    public User save(User user) {
    	if(user.getRole()!=null) {
    		user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
    	}
    	return saveClient(user);
    }
    
    public User updateNameByEmail(String email, String name) {
    	if(email == null || name == null || email.isBlank() || name.isBlank())
    		return null;
    	User user = findByEmail(email);
    	user.setName(name);
    	return userRepository.save(user);
    }
    
    public User updateSurnameByEmail(String email, String surname) {
    	if(email == null || surname == null || email.isBlank() || surname.isBlank())
    		return null;
    	User user = findByEmail(email);
    	user.setSurname(surname);
    	return userRepository.save(user);
    }
    
    public User updateAddressByEmail(String email, String address) {
    	if(email == null || address == null || email.isBlank() || address.isBlank())
    		return null;
    	User user = findByEmail(email);
    	user.setAddress(address);
    	return userRepository.save(user);
    }
 
    public User updateUser(User user) {
    	if(user.getRole() == null 
    		|| user.getEmail() == null
    		|| user.getName() == null
    		|| user.getSurname() == null
    		|| user.getAddress() == null
    	)
    		return null;
    	return userRepository.save(user);
    }
    
    public User saveClient(User user) {
    	Role userRole = roleEntityRepository.findByName("ROLE_CLIENT");
    	System.err.println(userRole);
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User saveAdmin(User user) {
    	Role userRole = roleEntityRepository.findByName("ROLE_ADMIN");
    	System.err.println(userRole);
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
 
    public List<User> findAll(){
        return userRepository.findAll();
    }
 
    public User findById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }
    
    public Role getRole(String email) {
    	User user = findByEmail(email);
    	return user.getRole();
    }
    
    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    
    
    
//    public boolean updateNameByEmail(String email, String name) {
//    	if(userRepository.updateNameByEmail(email, name) == 1)
//    		return true;
//    	return false;
//    }
}
