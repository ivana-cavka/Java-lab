package com.cavka.electricitymeasuring.services;

import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.repositories.ElectricityDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectricityDataService {
    @Autowired
    private ElectricityDataRepository repository;

    public ElectricityData save(ElectricityData entity) {
        return repository.save(entity);
    }

    //POANTA JE DA DO CONTROLLERA DOĐE ILI ČISTI ENTITY ILI SE U SERVISU THROWA EXCEPTION
    public ElectricityData findById(long id) {
        return repository.findById(id)
                .orElseGet(null);
    }

    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public List<ElectricityData> findAll() {
        List<ElectricityData> data = null;
        repository.findAll()
                .forEach(electricityData -> data.add(electricityData));
        if (data != null) {
            return data;
        } else {
            throw new RuntimeException("no data found");
        }
    }

    public List<ElectricityData> findAllById(Iterable<Long> longs) {
        List<ElectricityData> data = null;
        repository.findAll().
                forEach(electricityData -> data.add(electricityData));
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

    public void delete(ElectricityData entity) {
        repository.delete(entity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
