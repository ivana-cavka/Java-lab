package com.cavka.electricitymeasuring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "electricity_data")
@NoArgsConstructor
public class ElectricityData {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "time")
    private String time;

    @Column(name = "value")
    private Double value;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "device_id")
    private Device device;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "time = " + time + ", " +
                "value = " + value + ")";
    }

    public void setDate(String date) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}