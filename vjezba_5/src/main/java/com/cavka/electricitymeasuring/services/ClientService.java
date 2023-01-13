package com.cavka.electricitymeasuring.services;

import com.cavka.electricitymeasuring.entities.Client;
import com.cavka.electricitymeasuring.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client save(Client client) {
        return repository.save(client);
    }

    public Client findById(long id) {
        return repository.findById(id).orElseGet(null);
    }

    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public List<Client> findAll() {
        List<Client> data = new ArrayList<>();
        repository.findAll()
                .forEach(client -> data.add(client));
        if (data != null) {
            return data;
        } else {
            throw new RuntimeException("no data found");
        }
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void delete(Client entity) {
        repository.delete(entity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public boolean existsAddress(Client newClient){
        return repository.existsAddress(newClient);
    }

    public boolean existsDevice(Client newClient){
        return repository.existsDevice(newClient);
    }
}
