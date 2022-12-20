package com.cavka.electricitymeasuring.repositories;

import com.cavka.electricitymeasuring.entities.Adress;
import org.springframework.data.repository.CrudRepository;

public interface AdressRepository extends CrudRepository<Adress, Long> {
}