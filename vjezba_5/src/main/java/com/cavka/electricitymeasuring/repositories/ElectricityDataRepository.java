package com.cavka.electricitymeasuring.repositories;

import com.cavka.electricitymeasuring.entities.ElectricityData;
import org.springframework.data.repository.CrudRepository;

public interface ElectricityDataRepository extends CrudRepository<ElectricityData, Long> {
}