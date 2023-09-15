package com.demo.batteries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.batteries.handlers.RecordNotFoundException;
import com.demo.batteries.model.BatteryDetails;
import com.demo.batteries.model.BatteryEntity;
import com.demo.batteries.service.BatteryService;
 
@RestController
@RequestMapping("/battery")
public class BatteryController
{
    @Autowired
    BatteryService service;
 
	  
    @PostMapping(path="/createBatteries", consumes = "application/json")
	public ResponseEntity<List<BatteryEntity>> createBattery(@jakarta.validation.Valid @RequestBody List<BatteryEntity> batteryLst) throws RecordNotFoundException {
    	List<BatteryEntity> entity = service.createBatteries(batteryLst);
    	if(entity.size() > 0)
    		return new ResponseEntity<List<BatteryEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
    	else
    		return new ResponseEntity<List<BatteryEntity>>(entity, new HttpHeaders(), HttpStatus.NO_CONTENT);
	}
    
    @GetMapping(path="/batteries/{startcode}/{endcode}", produces = "application/json")
    public ResponseEntity<BatteryDetails> getBatteries(@PathVariable("startcode") String startPostCode , @PathVariable("endcode") String endPostCode) throws RecordNotFoundException {
    	BatteryDetails details = service.getBatteries(startPostCode,endPostCode);
 
    	if(details != null)
    		return new ResponseEntity<BatteryDetails>(details, new HttpHeaders(), HttpStatus.OK);
    	else
    		return new ResponseEntity<BatteryDetails>(details, new HttpHeaders(), HttpStatus.NO_CONTENT);
    }
    
	
}