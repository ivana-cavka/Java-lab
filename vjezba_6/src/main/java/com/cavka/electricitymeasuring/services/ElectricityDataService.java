package com.cavka.electricitymeasuring.services;

import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.repositories.ElectricityDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cavka.electricitymeasuring.dtos.ElectricityMeasuringDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ElectricityDataService {
    @Autowired
    private ElectricityDataRepository repository;
    private DeviceService deviceService;

    public ElectricityData save(ElectricityMeasuringDTO electricityDataDTO) {
        ElectricityData newData = new ElectricityData();
        newData.setDate(electricityDataDTO.getDate());
        newData.setTime(electricityDataDTO.getTime());
        newData.setValue(electricityDataDTO.getValue());
        newData.setDevice(deviceService.findById(electricityDataDTO.getDeviceId()));
        System.out.println(newData);
        return repository.save(newData);
    }

    public ElectricityData findById(long id) {
        return repository.findById(id)
                .orElseGet(null);
    }

    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public ArrayList<ElectricityData> findAll() {
        ArrayList<ElectricityData> data = new ArrayList<>();
        repository.findAll()
                .forEach(electricityData -> data.add(electricityData));
        if (data != null) {
            return data;
        } else {
            throw new RuntimeException("no data found");
        }
    }

    public List<ElectricityData> findAllById(Iterable<Long> longs) {
        List<ElectricityData> data = new ArrayList<>();
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
