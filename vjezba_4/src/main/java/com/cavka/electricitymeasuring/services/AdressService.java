package com.cavka.electricitymeasuring.services;

import com.cavka.electricitymeasuring.entities.Adress;
import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdressService {
    @Autowired
    private AdressRepository repository;

    public Adress save(Adress address) {
        return repository.save(address);
    }

    public Adress findById(long id) {
        return repository.findById(id)
                        .orElseGet(null);
    }

    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public List<Adress> findAll() {
        List<Adress> data = null;
        repository.findAll()
                .forEach(address -> data.add(address));
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

    public void delete(Adress address) {
        repository.delete(address);
    }

}
