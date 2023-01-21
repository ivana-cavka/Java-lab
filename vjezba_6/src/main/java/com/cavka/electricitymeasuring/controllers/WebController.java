package com.cavka.electricitymeasuring.controllers;

import com.cavka.electricitymeasuring.dtos.ElectricityMeasuringDTO;
import com.cavka.electricitymeasuring.entities.Device;
import com.cavka.electricitymeasuring.entities.ElectricityData;
import com.cavka.electricitymeasuring.services.ClientService;
import com.cavka.electricitymeasuring.services.DeviceService;
import com.cavka.electricitymeasuring.services.ElectricityDataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

@Controller
@AllArgsConstructor
public class WebController {

    ElectricityDataService electricityDataService;
    DeviceService deviceService;
    ClientService clientService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/input-measuring")
    public String showRecordForm(Model model) {
        model.addAttribute("measuring", new ElectricityMeasuringDTO());
        List<Device> devices = deviceService.findAll();
        model.addAttribute("devices",devices);
        return "form-measuring";
    }

    @PostMapping("/save-measuring")
    public String saveOrUpdateMeasuring(@ModelAttribute("measuring") ElectricityMeasuringDTO measuringDTO, BindingResult result) throws ParseException {
        if(result.hasErrors()) {
            return "form-measuring";
        }
        electricityDataService.save(measuringDTO);
        return "redirect:/measurings";
    }

    @GetMapping("/measurings")
    public String showMeasurings(Model model, Pageable page) {
        List<ElectricityData> measurings =  electricityDataService.findAll();
        model.addAttribute("measurings", measurings);
        return "show-measurings";
    }

    @GetMapping("/data-display")
    private String getData(Model model) {
        String uri = "http://localhost:8080/electricity_data";
        ElectricityData[] electricityData = restTemplate.getForObject(uri, ElectricityData[].class);
        System.out.println(electricityData.toString());
        model.addAttribute("measurings", electricityData);
        return "show-measurings";
    }

}