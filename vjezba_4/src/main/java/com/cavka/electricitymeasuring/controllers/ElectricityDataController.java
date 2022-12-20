package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.services.ElectricityDataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/electricity_data")
@AllArgsConstructor
@RestController
public class ElectricityDataController {
    @Autowired
    private ElectricityDataService service;

    @GetMapping()
    public ResponseEntity<List<ElectricityData>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.findAll());
    }

    // NE SMI BIT OPTIONAL, EVENTUALNO ITERABLE, AL NAJBOLJE OBJEKTI ILI LISTE ETC, MOŽE RESPONSE ENTITY
    @GetMapping("/{id}")
    public ElectricityData getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<ElectricityData> postElectricityData(@RequestBody ElectricityData newData){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newData));
    }
}