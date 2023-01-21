package com.cavka.electricitymeasuring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "device")
public class Device {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "device", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference
    private List<ElectricityData> electricityData = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ")";
    }
}