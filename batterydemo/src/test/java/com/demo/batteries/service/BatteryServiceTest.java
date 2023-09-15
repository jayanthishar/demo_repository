package com.demo.batteries.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;*/

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.batteries.dao.BatteryRepository;
import com.demo.batteries.handlers.RecordNotFoundException;
import com.demo.batteries.model.BatteryDetails;
import com.demo.batteries.model.BatteryEntity;
import com.demo.batteries.service.BatteryService;

@ExtendWith(MockitoExtension.class)
public class BatteryServiceTest {

	 @Mock
	 private BatteryRepository repository;
	 
	 @InjectMocks
	 private BatteryService batteryService;
	 
	private List<BatteryEntity> buildBatteries() {
			List<BatteryEntity> batteryLst = new ArrayList<BatteryEntity>();
			BatteryEntity battery1 = new BatteryEntity();
			battery1.setId((long) 10);
			battery1.setName("batt1");
			battery1.setPostcode("1000");
			battery1.setCapacity((long) 500);
			
			BatteryEntity battery2 = new BatteryEntity();
			battery2.setId((long) 11);
			battery2.setName("batt2");
			battery2.setPostcode("2000");
			battery2.setCapacity((long) 600);
							
			BatteryEntity battery3 = new BatteryEntity();
			battery3.setId((long) 12);
			battery3.setName("batt3");
			battery3.setPostcode("500");
			battery3.setCapacity((long) 800);
			
			batteryLst.add(battery1);
			batteryLst.add(battery2);
			batteryLst.add(battery3);
			
			return batteryLst;
		}
		
	 @Test
	 public void testCreateBatteries() throws RecordNotFoundException {
		 // Given
        // Employee employee = this.buildTestingEmployee();
         List<BatteryEntity> batteryLst = this.buildBatteries();
         // When
         when(repository.saveAll(batteryLst)).thenReturn(batteryLst);
         List<BatteryEntity> batteries = this.batteryService.createBatteries(batteryLst);
         // Then
         assertEquals(3, batteries.size());
         verify(this.repository).saveAll(batteryLst);
	 }
	 
	 @Test
	 public void testGetBatteries() throws RecordNotFoundException {
		 BatteryEntity battery1 = new BatteryEntity();
		 battery1.setId((long) 10);
		 battery1.setName("batt1");
		 battery1.setPostcode("1000");
		 battery1.setCapacity((long) 500);
		
		 BatteryEntity battery2 = new BatteryEntity();
		 battery2.setId((long) 11);
		 battery2.setName("batt2");
		 battery2.setPostcode("2000");
		 battery2.setCapacity((long) 600);
		 List<BatteryEntity> filteredLst = new ArrayList<BatteryEntity>();
		 filteredLst.add(battery1);
		 filteredLst.add(battery2);
		// When
         when(repository.findByPostcodeBetween("1000", "2000")).thenReturn(filteredLst);
         BatteryDetails batteryDtls = batteryService.getBatteries("1000", "2000");
        // Then
         assertNotNull(batteryDtls);
         verify(this.repository).findByPostcodeBetween("1000", "2000");
         assertEquals(2, batteryDtls.getName().size());
         assertEquals(OptionalDouble.of(550.0), batteryDtls.getAvgWattCapacity());     
	 }
	 
}
