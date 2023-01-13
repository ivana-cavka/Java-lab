package com.cavka.electricitymeasuring.repositories;

import com.cavka.electricitymeasuring.entities.Client;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Null;

public interface ClientRepository extends CrudRepository<Client, Long> {
    default boolean existsAdress(Client newClient) {
       return (newClient.getAddress()==null);
    }

    default boolean existsDevice(Client newClient) {
        return (newClient.getDevices().isEmpty() || newClient.getDevices()==null);
    }

    default boolean existsAddress(Client newClient) {
        return (newClient.getAddress().equals(null) || newClient.getDevices()==null);
    }
}