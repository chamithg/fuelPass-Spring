package com.codingdojo.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.models.User;
import com.codingdojo.models.UserLogin;
import com.codingdojo.repositories.UserRepsitory;


@Service
public class UserService {
	
	@Autowired
	private UserRepsitory userRep;
	
	
	public User registerUser(User newUser, BindingResult bindingResult) {
		Optional <User> checkUser = userRep.findByEmail(newUser.getEmail());
//		String allUsers = ((User) userRep.findAll()).getDlno();
		
		List <User> allUsers = userRep.findAll();
				
		if(checkUser.isPresent()) {
			bindingResult.rejectValue("email","Matches", "this email is already in use!");
			
		}
		
		for(User user : allUsers) {
			if(user.getDlno().equals(newUser.getDlno())) {
				bindingResult.rejectValue("dlno","Matches", "this driver's lisence is already registered. Please log in");
			}
			
		}
		
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword","Matches", "Password do Not match!");
			
		}
		
		if(bindingResult.hasErrors()) {
    		return null;
    	}
		
		
		String hashPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashPassword);
		
		
		return userRep.save(newUser);
	}
	
	
	public User loginUser(UserLogin loginUser, BindingResult bindingResult) {
		Optional <User> checkUser = userRep.findByEmail(loginUser.getEmail());
		
		
		if(!checkUser.isPresent()) {
			bindingResult.rejectValue("email","Matches", "this email does not exsist!");
			return null;
		}
		
		User currentUser = checkUser.get();
		
		if(!BCrypt.checkpw(loginUser.getPassword(), checkUser.get().getPassword())) {
			bindingResult.rejectValue("password","matches", "Password does not matches");
			return null;
		}
		
		return currentUser;
	}
	
	public User findById(Long id) {
		Optional <User> optionalUser = userRep.findById(id);		
		 if(optionalUser.isPresent()) {
	            return optionalUser.get();
	        } else {
	            return null;
	        }
	
	}
	
	public Boolean validateSession (HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		if(userId == null) {
			session.invalidate();
			return false;
		}
		return true;
		
	}

}
