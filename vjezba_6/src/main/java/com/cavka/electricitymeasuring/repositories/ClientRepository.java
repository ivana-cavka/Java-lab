package com.cavka.electricitymeasuring.repositories;

import com.cavka.electricitymeasuring.entities.Client;
import com.cavka.electricitymeasuring.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Null;
import java.util.Optional;

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