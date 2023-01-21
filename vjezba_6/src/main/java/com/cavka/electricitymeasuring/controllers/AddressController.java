package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.Address;
import com.cavka.electricitymeasuring.services.AddressService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/address")
@AllArgsConstructor
@RestController
public class AddressController {
    @Autowired
    private AddressService service;

    @GetMapping()
    public List<Address> getAll(Pageable page){
        return service.findAll(page);
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Address> postAdress(@RequestBody Address newAddress){
        if(!service.existsById(newAddress.getId())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newAddress));
        }
        return ResponseEntity.status(HttpStatus.IM_USED).body(newAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@RequestBody Address address,@PathVariable Long id){
        try{
            Address temp = service.findById(id);
            address.setId(id);
            service.save(address);
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
