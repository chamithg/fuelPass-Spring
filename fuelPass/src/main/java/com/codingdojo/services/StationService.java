package com.codingdojo.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.models.Station;
import com.codingdojo.models.StationLogin;
import com.codingdojo.repositories.StationRepository;



@Service
public class StationService {
	
	@Autowired
	private StationRepository stationRep;
	
	
	public Station registerStation(Station newStation, BindingResult bindingResult) {
		Optional <Station> checkStation = stationRep.findByEmail(newStation.getEmail());
				
		if(checkStation.isPresent()) {
			bindingResult.rejectValue("email","Matches", "this email is already in use!");
			
		}
		
		if(!newStation.getPassword().equals(newStation.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword","Matches", "Password do Not match!");
			
		}
		
		if(bindingResult.hasErrors()) {
    		return null;
    	}
		
		
		String hashPassword = BCrypt.hashpw(newStation.getPassword(), BCrypt.gensalt());
		newStation.setPassword(hashPassword);
		
		
		return stationRep.save(newStation);
	}
	
	
	public Station loginStation(StationLogin loginStation, BindingResult bindingResult) {
		Optional <Station> checkStation = stationRep.findByEmail(loginStation.getEmail());
		
		
		if(!checkStation.isPresent()) {
			bindingResult.rejectValue("email","Matches", "this email does not exsist!");
			return null;
		}
		
		Station currentStation = checkStation.get();
		
		if(!BCrypt.checkpw(loginStation.getPassword(), checkStation.get().getPassword())) {
			bindingResult.rejectValue("password","matches", "Password does not matches");
			return null;
		}
		
		return currentStation;
	}
	

	
	public Station findById(Long id) {
		Optional <Station> optionalStation = stationRep.findById(id);		
		 if(optionalStation.isPresent()) {
	            return optionalStation.get();
	        } else {
	            return null;
	        }
	
	}
	
	public Boolean validateSession (HttpSession session) {
		Long stationId = (Long)session.getAttribute("stationId");
		if(stationId == null) {
			session.invalidate();
			return false;
		}
		return true;
		
	}
	
	public void updateBal (Station station, Long amount) {
		
		station.setStock(station.getStock()-amount);
		stationRep.save(station);
		
	}

}
