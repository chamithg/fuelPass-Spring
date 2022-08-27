package com.codingdojo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.models.User;
import com.codingdojo.models.UserLogin;
import com.codingdojo.models.Vehicle;
import com.codingdojo.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	
	@GetMapping("/homeConsumer")
	public String displayHome(Model model, HttpSession session) {
		if(userServ.validateSession(session)) {
			Long userId = (Long) session.getAttribute("userId");
			List<Vehicle> allVehicles= userServ.findById(userId).getVehicles();
			model.addAttribute("vehicles", allVehicles);
			return "homeConsumer.jsp";
			
		}
		return "redirect:/consumer";
		
	}
	
	
	@GetMapping("/consumer")
	public String displayIndex(@ModelAttribute("newUser") User newUser, @ModelAttribute("loginAuth") UserLogin loginAuth) {
		return "loginConsumer.jsp";
	}
	
	@PostMapping("/process/consumer/registration")
	public String regirsterUser(@Valid @ModelAttribute("newUser") User newUser,
			
			BindingResult bindingResult,
			Model model,
			HttpSession session) {
		
		User createdUser = userServ.registerUser(newUser,bindingResult);
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("loginAuth", new UserLogin());
			
			
			return "loginConsumer.jsp";
		}
		
		
		
		session.setAttribute("name", createdUser.getName());
		session.setAttribute("userId", createdUser.getId());
		
		return "redirect:/homeConsumer";
	}
	
	@PostMapping("/process/consumer/login")
	public String loginUser (@Valid @ModelAttribute("loginAuth") UserLogin loginAuth,
			BindingResult bindingResult,
			Model model,
			HttpSession session ) {
		
		User currentUser = userServ.loginUser(loginAuth, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "loginConsumer.jsp";
		}
		session.setAttribute("name", currentUser.getName());
		session.setAttribute("userId", currentUser.getId());
		
		return "redirect:/homeConsumer";
	}
	
	@GetMapping("/process/consumer/logout")
	public String logoutUser(HttpSession session) {
		session.setAttribute("name", null);
		session.setAttribute("userId", null);
		
		return "redirect:/consumer";
	}
	

}
