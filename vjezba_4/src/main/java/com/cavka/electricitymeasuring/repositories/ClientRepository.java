package com.cavka.electricitymeasuring.repositories;

import com.cavka.electricitymeasuring.entities.Client;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Null;

public interface ClientRepository extends CrudRepository<Client, Long> {
    default boolean existsAddress(Client newClient) {
       return (newClient.getAdress()==null);
    }

    default boolean existsDevice(Client newClient) {
        return (newClient.getDevices().isEmpty() || newClient.getDevices()==null);
    }
}