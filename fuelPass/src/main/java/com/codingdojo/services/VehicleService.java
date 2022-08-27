package com.codingdojo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.models.Vehicle;
import com.codingdojo.repositories.VehicleRepository;

@Service
public class VehicleService {
	

	@Autowired
	private VehicleRepository vehiRepo;
	

    // returns all 
    public List<Vehicle> allVehicles() {
        return vehiRepo.findAll();
    }
    
    // creates -----------------------------------
    public  Vehicle createVehicle(Vehicle vehicle,BindingResult bindingResult) {
    	List <Vehicle> allVehicles = vehiRepo.findAll();


    	for(Vehicle each : allVehicles) {
    		if(each.getPlate().equals(vehicle.getPlate())) {
    			bindingResult.rejectValue("plate","Matches", "this vehicle is already registered.");
    		}
	    }
	
    	if(bindingResult.hasErrors()) {
    		return null;
    	}
    	System.out.println("3");

        return vehiRepo.save(vehicle);
    }
    
    
    // edit -------------------------------------------
    public  Vehicle editVehicle(Vehicle vehicle) {
        return vehiRepo.save(vehicle);
    }
    
    
    // retrieves single item
    public Vehicle findVehicle(Long id) {
        Optional<Vehicle> optionalVehicle = vehiRepo.findById(id);
        if(optionalVehicle.isPresent()) {
            return optionalVehicle.get();
        } else {
            return null;
        }
    }
    
    
    // delete
    public void deleteById (Long id) {
    	vehiRepo.deleteById(id);
    }

}
