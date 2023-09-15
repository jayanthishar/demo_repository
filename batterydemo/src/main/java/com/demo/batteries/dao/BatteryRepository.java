package com.demo.batteries.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.batteries.model.BatteryEntity;
 
@Repository
public interface BatteryRepository
        extends JpaRepository<BatteryEntity, Long> {

	List<BatteryEntity> findByPostcodeBetween(String start, String end);
}
