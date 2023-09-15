package com.demo.batteries.model;

import java.util.List;
import java.util.OptionalDouble;
import org.springframework.stereotype.Component;

@Component
public class BatteryDetails {


    private List<String> name;
    

    private OptionalDouble avgWattCapacity;
    
  
    private Long totalWattcapacity;
    
  

	public List<String> getName() {
		return name;
	}



	public void setName(List<String> name) {
		this.name = name;
	}



	public OptionalDouble getAvgWattCapacity() {
		return avgWattCapacity;
	}



	public void setAvgWattCapacity(OptionalDouble avgCapacity) {
		this.avgWattCapacity = avgCapacity;
	}



	public Long getTotalWattcapacity() {
		return totalWattcapacity;
	}



	public void setTotalWattcapacity(Long totalWattcapacity) {
		this.totalWattcapacity = totalWattcapacity;
	}



	@Override
    public String toString() {
        return "BatteryDetails [name=" + name + 
                ", avgWattCapacity=" + avgWattCapacity + ", totalWattCapacity=" + totalWattcapacity   + "]";
    }
}