package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.services.ElectricityDataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.sql.Time;
import java.util.*;

@RequestMapping("/electricity_data")
@AllArgsConstructor
@RestController
public class ElectricityDataController {
    @Autowired
    private ElectricityDataService service;

    @GetMapping()
    public List<ElectricityData> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ElectricityData getById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping(value="/year")//triba ić sve u jedan isti path, ali sa različitim parametrima - hash map
    @ResponseBody
    public  ResponseEntity<String> getYear(@RequestParam("year") Integer searchYear){
            try{
                List<ElectricityData> allData = service.findAll();
                double sumAll = 0.0;
                for (ElectricityData data : allData) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(data.getDate());
                    Integer yearInDate = cal.get(Calendar.YEAR);
                    if (searchYear.equals(yearInDate)) {
                        sumAll = Double.sum(sumAll,data.getValue());
                    }
                }
                System.out.print("year " + searchYear + " total spent " + sumAll);
                return new ResponseEntity<>("year " + searchYear + " total spent " + sumAll,HttpStatus.OK);
            }
            catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    @RequestMapping(value="/year+month", method=RequestMethod.GET) //@ResponseBody
    public  ResponseEntity<String> getYearAndMonth(@RequestParam("year") Integer searchYear, @RequestParam("month") Integer searchMonth) {
            try{
                List<ElectricityData> allData = service.findAll();
                double sumAll = 0.0;
                for (ElectricityData data : allData) {
                    System.out.print(data.toString());
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(data.getDate());
                    Integer monthInDate = cal.get(Calendar.MONTH)+1;
                    Integer yearInDate = cal.get(Calendar.YEAR);
                    if (searchYear.equals(yearInDate) && searchMonth.equals(monthInDate)) {
                        sumAll = Double.sum(sumAll,data.getValue());
                    }
                }
                System.out.print("year " + searchYear + " month " + searchMonth + " total spent " + sumAll);
                return new ResponseEntity<>("year " + searchYear + " month " + searchMonth + " total spent " + sumAll,HttpStatus.OK);
            }
            catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

    @GetMapping(value="/all_year") //vratit json, priko hash mape
    @ResponseBody
    public  ResponseEntity<String> getYearByMonths(@RequestParam("year") Integer searchYear) {
        try {
            List<ElectricityData> allData = service.findAll();
            double sum1 = 0;
            double sum2 = 0;
            double sum3 = 0;
            double sum4 = 0;
            double sum5 = 0;
            double sum6 = 0;
            double sum7 = 0;
            double sum8 = 0;
            double sum9 = 0;
            double sum10 = 0;
            double sum11 = 0;
            double sum12 = 0;
            for (ElectricityData data : allData) {
                System.out.print(data.toString());
                Calendar cal = Calendar.getInstance();
                cal.setTime(data.getDate());
                int monthInDate = cal.get(Calendar.MONTH)+1;
                int yearInDate = cal.get(Calendar.YEAR);
                if (searchYear.equals(yearInDate)) {
                    switch (monthInDate) {
                        case 1:
                            sum1 += data.getValue();
                            break;
                        case 2:
                            sum2 += data.getValue();
                            break;
                        case 3:
                            sum3 += data.getValue();
                            break;
                        case 4:
                            sum4 += data.getValue();
                            break;
                        case 5:
                            sum5 += data.getValue();
                            break;
                        case 6:
                            sum6 += data.getValue();
                            break;
                        case 7:
                            sum7 += data.getValue();
                            break;
                        case 8:
                            sum8 += data.getValue();
                            break;
                        case 9:
                            sum9 += data.getValue();
                            break;
                        case 10:
                            sum10 += data.getValue();
                            break;
                        case 11:
                            sum11 += data.getValue();
                            break;
                        case 12:
                            sum12 += data.getValue();
                            break;
                        default:
                            break;
                    }
                }
            }
            String message = "year " + searchYear + " by month: ";
            for (int i = 1; i <= 12; i++) {
                switch (i) {
                    case 1:
                        message += "\nJanuary: " + sum1;
                        break;
                    case 2:
                        message += "\nFebruary: " + sum2;
                        break;
                    case 3:
                        message += "\nMarch: " + sum3;
                        break;
                    case 4:
                        message += "\nApril: " + sum4;
                        break;
                    case 5:
                        message += "\nMay: " + sum5;
                        break;
                    case 6:
                        message += "\nJune: " + sum6;
                        break;
                    case 7:
                        message += "\nJuly: " + sum7;
                        break;
                    case 8:
                        message += "\nAugust: " + sum8;
                        break;
                    case 9:
                        message += "\nSeptember: " + sum9;
                        break;
                    case 10:
                        message += "\nOctober: " + sum10;
                        break;
                    case 11:
                        message += "\nNovember: " + sum11;
                        break;
                    case 12:
                        message += "\nDecember: " + sum12;
                        break;
                    default:
                        break;
                }
            }
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<ElectricityData> postElectricityData(@RequestBody ElectricityData newData){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElectricityData> update(@RequestBody ElectricityData elData, @PathVariable Long id){
        try{
            ElectricityData temp = service.findById(id);
            elData.setId(id);
            service.save(elData);
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