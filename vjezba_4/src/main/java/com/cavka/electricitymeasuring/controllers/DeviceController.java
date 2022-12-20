package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.Client;
import com.cavka.electricitymeasuring.entities.Device;
import com.cavka.electricitymeasuring.services.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/device")
@AllArgsConstructor
@RestController
public class DeviceController {
    @Autowired
    private DeviceService service;

    @GetMapping()
    public List<Device> getAll(){
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
}