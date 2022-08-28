package com.codingdojo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.models.FuelReceipt;
import com.codingdojo.models.Vehicle;
import com.codingdojo.services.UserService;
import com.codingdojo.services.VehicleService;

@Controller
public class VehicleController {
	
	@Autowired
	private VehicleService vehiServ;
	
	@Autowired
	private UserService userServ;
	
	
	@GetMapping("/homeConsumer/viewVehicle/{id}")
	 public String display(@PathVariable("id") Long id, Model model,HttpSession session) {
		if(userServ.validateSession(session)) {
	        Vehicle vehicle = vehiServ.findVehicle(id);
			List <FuelReceipt> allReceipts = vehicle.getRecipts();
			model.addAttribute("receipts",allReceipts);
	        model.addAttribute("vehicle", vehicle);
	        return "viewVehicle.jsp";
		}
		return "redirect:/consumer";
    }
	
	@GetMapping("/homeConsumer/addVehicle")
	public String displayVehicleCreate(@ModelAttribute("newVehicle") Vehicle newVehicle,HttpSession session) {
		if(userServ.validateSession(session)) {
			return "addVehicle.jsp";
		}
		return "redirect:/consumer";
	}
	
	@PostMapping("/process/vehicle/create")
	public String createVehicle(@Valid @ModelAttribute("newVehicle") Vehicle newVehicle,
			BindingResult bindingResult,
			Model model) {
		
	

		vehiServ.createVehicle(newVehicle,bindingResult );		
		
		if(bindingResult.hasErrors()) {	 
			return "addVehicle.jsp";
		}
		
		return "redirect:/homeConsumer";
		

	}
	
	@GetMapping("/homeConsumer/editVehicle/{id}")
	 public String displayEdit(@ModelAttribute("currentVehicle") Vehicle currentVehicle,
			 BindingResult bindingResult,
			 HttpSession session,
			 @PathVariable("id") Long id,
			 Model model) {
		if(userServ.validateSession(session)) {
	      Vehicle vehicle = vehiServ.findVehicle(id); 
	      
	      	model.addAttribute("currentVehicle", vehicle);
	      	return "editVehicle.jsp";
		}
		return "redirect:/consumer";
   }
	
	@PutMapping("/process/vehicle/update/{id}")
	public String updateVehicle(@Valid@ModelAttribute("currentVehicle") Vehicle currentVehicle,
			BindingResult bindingResult,Model model,
			@PathVariable("id") Long id) {
		
		currentVehicle.setId(id);
		vehiServ.editVehicle(currentVehicle);

		if(bindingResult.hasErrors()) {	 
			model.addAttribute("currentVehicle", currentVehicle);
			return "editVehicle.jsp";
		}
		return "redirect:/homeConsumer";
		
	}
	@GetMapping("/homeConsumer/editQuota/{id}")
	 public String displayEditQuota(@ModelAttribute("currentVehicle") Vehicle currentVehicle,
			 BindingResult bindingResult,
			 HttpSession session,
			 @PathVariable("id") Long id,
			 Model model) {
		if(userServ.validateSession(session)) {
	      Vehicle vehicle = vehiServ.findVehicle(id); 
	      
	      	model.addAttribute("currentVehicle", vehicle);
	      	return "editQuota.jsp";
		}
		return "redirect:/consumer";
  }
	
	
	@GetMapping("process/vehicle/delete/{id}")
	public String deleteVehicle(@PathVariable("id") Long id,HttpSession session) {
		if(userServ.validateSession(session)) {
			
			vehiServ.deleteById(id);
			
			return "redirect:/homeConsumer";
		}
		return "redirect:/consumer";
		
	}
	
	@PostMapping("/process/quota/enhance/{id}")
	public String retrieveCar(@RequestParam(value ="date") String date,
			@RequestParam(value ="today") String today,
			@RequestParam(value ="odo") long odo,
			@RequestParam(value ="curr") long curr,
			@RequestParam(value ="eco") long eco,
			@PathVariable("id") long id,
			Model model, RedirectAttributes redirectAttributes) throws ParseException {

		

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date prevDate = sdf.parse(date);
		Date currDate = sdf.parse(today);
		
		
		
		long driven = curr - odo;
		long numDays = (currDate.getTime()- prevDate.getTime())/ (24 * 60 * 60 * 1000);
		long dailyAvgMiles = (long) Math.ceil(driven/ numDays);
		long newQuota = (long) Math.ceil((dailyAvgMiles)/eco*7);
		Vehicle vehicle = vehiServ.findVehicle(id);
		
		redirectAttributes.addFlashAttribute("numDays", numDays);
		redirectAttributes.addFlashAttribute("dailyAvgMiles", dailyAvgMiles);		
		redirectAttributes.addFlashAttribute("newQuota", newQuota);
		redirectAttributes.addFlashAttribute("driven", driven);
		redirectAttributes.addFlashAttribute("usedFuel", vehicle.getQuota()-vehicle.getBalance());
	
		return "redirect:/homeConsumer/editQuota/{id}";
	}

}
