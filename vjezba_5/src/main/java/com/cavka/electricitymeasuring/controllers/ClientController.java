package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.Client;
import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/client")
@AllArgsConstructor
@RestController
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping()
    public Iterable<Client> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping()//provjera postoj li taj id, ima li pripadni entitet
    public ResponseEntity<Client> postClient(@RequestBody Client newClient){
        if(service.existsById(newClient.getId())) {
            return new ResponseEntity(HttpStatus.IM_USED);
        }
        List<Client> clientList = (List<Client>) service.findAll();
        for(int i=0;i<clientList.size();i++){
            if(!service.existsAddress(newClient)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            if(!service.existsDevice(newClient)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        service.save(newClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@RequestBody Client client,@PathVariable Long id){
        try{
            Client temp = service.findById(id);
            client.setId(id);
            service.save(client);
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
