package com.cavka.electricitymeasuring.repositories;

import com.cavka.electricitymeasuring.entities.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {
}