package com.cavka.electricitymeasuring.services;

import com.cavka.electricitymeasuring.entities.Address;
import com.cavka.electricitymeasuring.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    public Address save(Address address) {
        return repository.save(address);
    }

    public Address findById(long id) {
        return repository.findById(id)
                .orElseGet(null);
    }

    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public List<Address> findAll(Pageable page) {
        List<Address> data = new ArrayList<>();
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

    public void delete(Address address) {
        repository.delete(address);
    }

}
