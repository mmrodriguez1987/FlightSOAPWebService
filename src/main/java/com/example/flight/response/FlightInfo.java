/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.flight.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalTime;

/**
 * Represents a single flight result returned by our service.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlightInfo", propOrder = {
    "airlineCode",
    "flightNumber",
    "departureTime",
    "arrivalTime",
    "fare"
})
public class FlightInfo {

    private String airlineCode;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private double fare;

    public FlightInfo() { }

    public FlightInfo(String airlineCode, String flightNumber,
                      LocalTime departureTime, LocalTime arrivalTime,
                      double fare) {
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime.toString();
        this.arrivalTime = arrivalTime.toString();
        this.fare = fare;
    }

    // … getters and setters …

    public String getAirlineCode() {
        return airlineCode;
    }
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getFare() {
        return fare;
    }
    public void setFare(double fare) {
        this.fare = fare;
    }
}
