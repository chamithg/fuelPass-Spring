package com.codingdojo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.models.FuelReceipt;

import com.codingdojo.models.Vehicle;
import com.codingdojo.repositories.FuelReceiptRepository;
import com.codingdojo.repositories.VehicleRepository;




@Service
public class FuelReceiptService {
	
	@Autowired
	private FuelReceiptRepository receiptRepo;
	
	@Autowired 
	private VehicleRepository vehiRepo;
	

	
	
	
	
	public  FuelReceipt createReceipt(FuelReceipt receipt,BindingResult bindingResult) {
		System.out.println(receipt.getAmount());
		System.out.println(receipt.getStation());
		System.out.println(receipt.getVehicle());
		if(receipt.getAmount()>receipt.getVehicle().getBalance()) {
			bindingResult.rejectValue("amount","Matches", "Issue amount exceeds balance of the quota.");
		}
		
		if(receipt.getAmount()>receipt.getStation().getStock()) {
			bindingResult.rejectValue("amount","Matches", "The fuel stock is not enough for this transaction.");
		}
		
	    if(bindingResult.hasErrors()) {
	    	return null;
	    }
	    
	    Vehicle vehicle = receipt.getVehicle();
	    Long balance = vehicle.getBalance()-receipt.getAmount();
	    vehicle.setBalance(balance);
	    vehiRepo.save(vehicle);
	    
//	    Station station = receipt.getStation();
//	    Long stock = station.getStock()-receipt.getAmount();
//	    station.setStock(stock);
//	    stationRepo.save(station);
	    
	    
	        return receiptRepo.save(receipt);
	    }
	
	public List<FuelReceipt> allReceipts() {
	        return receiptRepo.findAll();
	    }
	    
	
	

}
