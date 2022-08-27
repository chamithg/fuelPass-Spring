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

import com.codingdojo.models.FuelReceipt;
import com.codingdojo.models.Station;
import com.codingdojo.models.StationLogin;
import com.codingdojo.services.StationService;

@Controller
public class StationController {
	
	@Autowired
	private StationService stationServ;
	
	@GetMapping("/homeFilling")
	public String displayHome(Model model, HttpSession session) {
		if(stationServ.validateSession(session)) {
			Long stationId = (Long) session.getAttribute("stationId");
			List <FuelReceipt> allReceipts = stationServ.findById(stationId).getRecipts();
			int issuedTotal = 0;
			for(FuelReceipt each:allReceipts) {
				issuedTotal+=each.getAmount();
			}
			
			model.addAttribute("station", stationServ.findById(stationId));
			model.addAttribute("receipts",allReceipts);
			model.addAttribute("issued",issuedTotal);
			return "homeFilling.jsp";
			
		}
		return "redirect:/fillingStation";
		
	}
	
	@GetMapping("/fillingStation")
	public String displayIndex(@ModelAttribute("newStation") Station newStation, @ModelAttribute("loginStation") StationLogin loginStation) {
		return "loginFilling.jsp";
	}
	
	@PostMapping("/process/filling/registration")
	public String regirsterStation(@Valid @ModelAttribute("newStation") Station newStation,
			
			BindingResult bindingResult,
			Model model,
			HttpSession session) {
		
		Station createdStation = stationServ.registerStation(newStation,bindingResult);
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("loginStation", new StationLogin());
			
			
			return "loginFilling.jsp";
		}
		
		
		
		session.setAttribute("name", createdStation.getName());
		session.setAttribute("stationId", createdStation.getId());
		
		return "redirect:/homeFilling";
	}
	
	@PostMapping("/process/filling/login")
	public String loginStation (@Valid @ModelAttribute("loginStation") StationLogin loginStation,
			BindingResult bindingResult,
			Model model,
			HttpSession session ) {
		
		Station currentStation = stationServ.loginStation(loginStation, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("newStation", new Station());
			return "loginFilling.jsp";
		}
		session.setAttribute("name", currentStation.getName());
		session.setAttribute("stationId", currentStation.getId());
		
		return "redirect:/homeFilling";
	}
	
	@GetMapping("/process/filling/logout")
	public String logoutUser(HttpSession session) {
		session.setAttribute("name", null);
		session.setAttribute("stationId", null);
		
		return "redirect:/fillingStation";
	}
	

}
