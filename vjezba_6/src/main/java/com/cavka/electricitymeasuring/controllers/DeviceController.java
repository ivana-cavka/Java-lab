package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.Client;
import com.cavka.electricitymeasuring.entities.Device;
import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.services.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequestMapping("/device")
@AllArgsConstructor
@RestController
public class DeviceController {
    @Autowired
    private DeviceService service;

    @GetMapping()
    public List<Device> getAll(Pageable page){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Device getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Device> postDevice(@RequestBody Device newDevice){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newDevice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> update(@RequestBody Device device, @PathVariable Long id){
        try{
            Device temp = service.findById(id);
            device.setId(id);
            service.save(device);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}