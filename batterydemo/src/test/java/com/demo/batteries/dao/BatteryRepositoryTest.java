package com.demo.batteries.dao;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.batteries.dao.BatteryRepository;
import com.demo.batteries.model.BatteryEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class BatteryRepositoryTest {

	@Autowired
	private BatteryRepository repository;
	
	private List<BatteryEntity> setupData() {
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
	public void testCreateBatteries() {
		
		/*
		 * List<BatteryEntity> batteryLst = new ArrayList<BatteryEntity>();
		 * BatteryEntity battery1 = new BatteryEntity(); battery1.setId((long) 10);
		 * battery1.setName("batt1"); battery1.setPostcode("1000");
		 * battery1.setCapacity((long) 500);
		 * 
		 * BatteryEntity battery2 = new BatteryEntity(); battery2.setId((long) 11);
		 * battery2.setName("batt2"); battery2.setPostcode("2000");
		 * battery2.setCapacity((long) 600);
		 * 
		 * batteryLst.add(battery1); batteryLst.add(battery2);
		 */
		 
		
		List<BatteryEntity> insBatteryLst = this.repository.saveAll(setupData());
		assertNotNull(insBatteryLst);
        assertEquals("batt1", insBatteryLst.get(0).getName());
        assertEquals("batt2", insBatteryLst.get(1).getName());
        assertEquals("batt3", insBatteryLst.get(2).getName());
        assertEquals(3, insBatteryLst.size());
		
	}
	
	@Test
	public void testFindByPostCodeBetween() {
		this.repository.saveAll(setupData());
		List<BatteryEntity> batteryList = repository.findByPostcodeBetween("1000", "2000");
		
		assertNotNull(batteryList);
		assertEquals(2, batteryList.size());
        assertEquals("1000", batteryList.get(0).getPostcode());
        assertEquals("2000", batteryList.get(1).getPostcode());
		
	}
	
}
