package com.cavka.electricitymeasuring.services;

import com.cavka.electricitymeasuring.entities.Device;
import com.cavka.electricitymeasuring.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository repository;

    public Device save(Device device) {
        return repository.save(device);
    }

    public Device findById(long id) {
        return repository.findById(id)
                .orElseGet(null);
    }

    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public List<Device> findAll() {
        List<Device> data = new ArrayList<>();
        repository.findAll()
                .forEach(device -> data.add(device));
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

    public void delete(Device entity) {
        repository.delete(entity);
    }


}
