package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.services.ElectricityDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@RequestMapping("/electricity_data")
@AllArgsConstructor
@RestController
public class ElectricityDataController {

    private ElectricityDataService service;

    @GetMapping()
    public ResponseEntity<List<ElectricityData>> getAll(Pageable page) throws IOException {
        List<ElectricityData> list = service.findAll();
        System.err.println("hey"+list.size());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ElectricityData getById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping(value="/year")//triba ić sve u jedan isti path, ali sa različitim parametrima - hash map
    @ResponseBody
    public  ResponseEntity<JSONObject> getYear(@RequestParam("year") Integer searchYear, Pageable page){
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
                Map<String, Object> map = new HashMap<>();
                map.put("year", searchYear);
                map.put("sum", sumAll);
                JSONObject json = new JSONObject(map);
                return new ResponseEntity<>(json,HttpStatus.OK);
            }
            catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    @RequestMapping(value="/year+month", method=RequestMethod.GET) //@ResponseBody
    public  ResponseEntity<JSONObject> getYearAndMonth(@RequestParam("year") Integer searchYear, @RequestParam("month") Integer searchMonth, Pageable page) {
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
                Map<String, Object> map = new HashMap<>();
                map.put("year", searchYear);
                map.put("month",searchMonth);
                map.put("sum", sumAll);
                JSONObject json = new JSONObject(map);
                return new ResponseEntity<>(json,HttpStatus.OK);
            }
            catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

    @GetMapping(value="/all-year")
    @ResponseBody
    public  ResponseEntity<JSONObject> getYearByMonths(@RequestParam("year") Integer searchYear, Pageable page) {
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
            Map<String, Object> map = new HashMap<>();
            map.put("year", searchYear);
            Map<String, Object> mapMonths = new HashMap<>();
            mapMonths.put("January",sum1);
            mapMonths.put("February",sum2);
            mapMonths.put("March",sum3);
            mapMonths.put("April",sum4);
            mapMonths.put("May",sum5);
            mapMonths.put("June",sum6);
            mapMonths.put("July",sum7);
            mapMonths.put("August",sum8);
            mapMonths.put("September",sum9);
            mapMonths.put("October",sum10);
            mapMonths.put("November",sum11);
            mapMonths.put("December",sum12);
            map.put("month", mapMonths);
            JSONObject json = new JSONObject(map);
            return new ResponseEntity<>(json,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@PostMapping()
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
    }*/
}