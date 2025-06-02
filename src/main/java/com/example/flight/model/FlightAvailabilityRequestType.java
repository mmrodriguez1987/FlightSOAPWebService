package com.example.flight.model;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "flightAvailabilityRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlightAvailabilityRequestType")
public class FlightAvailabilityRequestType {

    @XmlElement(required = true)
    private String flightNumber;

    @XmlElement(required = true)
    private String origin;

    @XmlElement(required = true)
    private String destination;

    @XmlElement(required = true)
    private Date departureDate;

    @XmlElement
    private Date arrivalDate;

    @XmlElement
    private int availableSeats;

    @XmlElement
    private double price;

    @XmlElement
    private Passengers passengers;

    public FlightAvailabilityRequestType() {
    }

    // Basic getters and setters
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Date getDepartureDate() { return departureDate; }

    public void setDepartureDate(Date departureDate) { this.departureDate = departureDate; }

    // String version to handle the LocalDate in client code
    public void setDepartureDate(String dateStr) {
        // Convert the string date to a java.util.Date
        try {
            LocalDate localDate = LocalDate.parse(dateStr);
            this.departureDate = java.sql.Date.valueOf(localDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr, e);
        }
    }

    public Date getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(Date arrivalDate) { this.arrivalDate = arrivalDate; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Passengers getPassengers() { return passengers; }
    public void setPassengers(Passengers passengers) { this.passengers = passengers; }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Passengers {

        @XmlElement(name = "passenger")
        private List<PassengerCountType> passenger;

        public Passengers() {
            passenger = new ArrayList<>();
        }

        public List<PassengerCountType> getPassenger() {
            return passenger;
        }

        public void setPassenger(List<PassengerCountType> passenger) {
            this.passenger = passenger;
        }
    }
}