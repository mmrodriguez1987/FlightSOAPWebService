/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.flight.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple wrapper that holds a list of FlightInfo entries.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlightAvailabilityResponse", propOrder = {
    "flights"
})
public class FlightAvailabilityResponse {

    @XmlElement(name = "Flight", required = true)
    private List<FlightInfo> flights;

    public FlightAvailabilityResponse() {
        this.flights = new ArrayList<>();
    }

    public List<FlightInfo> getFlights() {
        return flights;
    }
    public void setFlights(List<FlightInfo> flights) {
        this.flights = flights;
    }

    public void addFlight(FlightInfo fi) {
        this.flights.add(fi);
    }
}
