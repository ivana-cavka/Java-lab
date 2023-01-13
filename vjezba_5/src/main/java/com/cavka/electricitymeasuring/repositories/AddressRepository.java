package com.cavka.electricitymeasuring.repositories;

import com.cavka.electricitymeasuring.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}