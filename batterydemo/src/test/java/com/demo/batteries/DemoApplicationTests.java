package com.demo.batteries;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.batteries.controllers.BatteryController;

@SpringBootTest
public class DemoApplicationTests {
	 @Autowired
	  BatteryController batteryController;
	 
	@Test
	public void contextLoads() {
		Assertions.assertThat(batteryController).isNotNull();
	}

}
