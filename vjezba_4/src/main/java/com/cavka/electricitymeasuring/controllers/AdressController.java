package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.Adress;
import com.cavka.electricitymeasuring.services.AdressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/address")
@AllArgsConstructor
@RestController
public class AdressController {
    @Autowired
    private AdressService service;

    @GetMapping()
    public List<Adress> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Adress getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Adress> postAdress(@RequestBody Adress newAdress){
        if(!service.existsById(newAdress.getId())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newAdress));
        }
        return ResponseEntity.status(HttpStatus.IM_USED).body(newAdress);
    }
}
