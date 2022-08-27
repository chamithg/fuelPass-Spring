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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.models.FuelReceipt;
import com.codingdojo.models.Vehicle;
import com.codingdojo.services.FuelReceiptService;
import com.codingdojo.services.StationService;
import com.codingdojo.services.VehicleService;


@Controller
public class FuelReceiptController {
	
	@Autowired
	private FuelReceiptService receiptServ;
	
	@Autowired
	private StationService stationServ;
	
	@Autowired
	private VehicleService vehiServ;
	
	

	
	@GetMapping("/homeFilling/issueFuel")
	public String issueFuel(@ModelAttribute("receipt") FuelReceipt receipt,HttpSession session) {
		if(stationServ.validateSession(session)) {
			return "issueFuel.jsp";
		}
		return "redirect:/fillingStation";
	}
	
	@PostMapping("/homeFilling/retrieveCar")
	public String retrieveCar(@RequestParam(value ="plate") String plate, Model model, RedirectAttributes redirectAttributes) {
		
		List <Vehicle> allVehis = vehiServ.allVehicles();
		
		System.out.println(plate);
		
		for(Vehicle each: allVehis) {
			if(each.getPlate().equals(plate)) {
				System.out.println("Hello");
				System.out.println(each);
				redirectAttributes.addFlashAttribute("vehicle", each);
				return "redirect:/homeFilling/issueFuel";						
			}
		}
		redirectAttributes.addFlashAttribute("error", "No vehicles registered under this plate");
		return "redirect:/homeFilling/issueFuel";
		
		
	}
	
	@PostMapping("/process/receipt/create")
	public String createVehicle(@Valid @ModelAttribute("receipt") FuelReceipt receipt,
			BindingResult bindingResult,
			Model model) {

		receiptServ.createReceipt(receipt,bindingResult);		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("vehicle",receipt.getVehicle());
			return "issueFuel.jsp";
		}
		
//		stationServ.updateBal(receipt.getStation(), receipt.getAmount());
		
		return "redirect:/homeFilling";
		

	}
	

	

}
