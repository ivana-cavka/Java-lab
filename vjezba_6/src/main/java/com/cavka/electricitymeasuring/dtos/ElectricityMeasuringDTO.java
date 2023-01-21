package com.cavka.electricitymeasuring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectricityMeasuringDTO {

    Double value;

    String time;

    String date;

    long deviceId;

}