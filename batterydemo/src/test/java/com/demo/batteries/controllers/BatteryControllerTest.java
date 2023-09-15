package com.demo.batteries.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.demo.batteries.model.BatteryDetails;
import com.demo.batteries.model.BatteryEntity;
import com.demo.batteries.service.BatteryService;


@WebMvcTest(BatteryController.class)
public class BatteryControllerTest {

  @MockBean
  BatteryService batteryService;

  @Autowired
  MockMvc mockMvc;

  @Test
  public void testfindBatteries() throws Exception {
		BatteryDetails batteryDtls = new BatteryDetails();
		List<String> names = Arrays.asList("name1","name2");
		batteryDtls.setName(names);
		batteryDtls.setAvgWattCapacity(OptionalDouble.of(100.0));
		batteryDtls.setTotalWattcapacity((long)2000);

    Mockito.when(batteryService.getBatteries("500", "2000")).thenReturn(batteryDtls);

    mockMvc.perform(get("/battery/batteries/500/2000"))
        .andExpect(status().isOk());
  }

}
