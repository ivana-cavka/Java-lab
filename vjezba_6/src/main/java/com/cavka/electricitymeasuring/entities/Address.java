package com.cavka.electricitymeasuring.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "adress")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    public Address() {

    }

    public Address(String newCity, String newStreet, String newPostcode) {
        street = newStreet;
        city = newCity;
        postcode = newPostcode;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "street = " + street + ", " +
                "city = " + city + ", " +
                "postcode = " + postcode + ")";
    }
}