package com.demo.batteries.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.batteries.dao.BatteryRepository;
import com.demo.batteries.handlers.RecordNotFoundException;
import com.demo.batteries.model.BatteryDetails;
import com.demo.batteries.model.BatteryEntity;

@Service
public class BatteryService {

	@Autowired
	private BatteryRepository repository;

	
	public BatteryDetails getBatteries(String startPostCode, String endPostCode) throws RecordNotFoundException{
		
		if(startPostCode != null && endPostCode != null && Long.parseLong(startPostCode) >=0 && Long.parseLong(endPostCode) >=0) {
			List<BatteryEntity> batteryList = repository.findByPostcodeBetween(startPostCode, endPostCode);
			BatteryDetails batteryDetails = new BatteryDetails();
			if (batteryList.size() > 0) {
				
				batteryDetails.setName(batteryList.stream().map(e -> e.getName()).collect(Collectors.toList()).stream()
						.sorted().collect(Collectors.toList()));
				batteryDetails.setAvgWattCapacity(batteryList.stream().mapToLong(c1 -> c1.getCapacity()).average());
				batteryDetails.setTotalWattcapacity(batteryList.stream().mapToLong(c1 -> c1.getCapacity()).sum());

				return batteryDetails;
			} else {
				 return null;
			}
		}
		return null;
	}

	
	public List<BatteryEntity> createBatteries(List<BatteryEntity> batteryLst) throws RecordNotFoundException {
		batteryLst = repository.saveAll(batteryLst);
		return batteryLst;
	}

	
}