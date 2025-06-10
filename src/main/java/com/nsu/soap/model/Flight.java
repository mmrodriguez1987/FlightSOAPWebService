// src/main/java/com/nsu/soap/model/Flight.java

package com.nsu.soap.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@Entity
@Table(name = "Flights")
@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(required = true)
    @Column(nullable = false)
    private String origin;

    @XmlElement(required = true)
    @Column(nullable = false)
    private String destination;

    @XmlElement(required = true)
    @Column(nullable = false)
    private String departureDate;

    @XmlElement(required = true)
    @Column(nullable = false)
    private Integer passengers;

    @XmlElement
    private String timeRange;

    @XmlElementWrapper(name = "preferredConnectingCities")
    @XmlElement(name = "city")
    private List<String> preferredConnectingCities;

    @XmlElementWrapper(name = "preferredAirlines")
    @XmlElement(name = "airline")
    private List<String> preferredAirlines;

    @XmlElementWrapper(name = "flightTypes")
    @XmlElement(name = "type")
    private List<String> flightTypes;

    @XmlElement
    private String specificAirline;

    @XmlElement
    private String specificFlight;

    public Flight() {
        // no-arg constructor required by JPA and JAXB
    }

    public Flight(String origin,
                  String destination,
                  String departureDate,
                  Integer passengers,
                  String timeRange,
                  List<String> preferredConnectingCities,
                  List<String> preferredAirlines,
                  List<String> flightTypes,
                  String specificAirline,
                  String specificFlight) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.passengers = passengers;
        this.timeRange = timeRange;
        this.preferredConnectingCities = preferredConnectingCities;
        this.preferredAirlines = preferredAirlines;
        this.flightTypes = flightTypes;
        this.specificAirline = specificAirline;
        this.specificFlight = specificFlight;
    }

    // ─── Getters and Setters ─────────────────────────────────────────────────

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public List<String> getPreferredConnectingCities() {
        return preferredConnectingCities;
    }

    public void setPreferredConnectingCities(List<String> preferredConnectingCities) {
        this.preferredConnectingCities = preferredConnectingCities;
    }

    public List<String> getPreferredAirlines() {
        return preferredAirlines;
    }

    public void setPreferredAirlines(List<String> preferredAirlines) {
        this.preferredAirlines = preferredAirlines;
    }

    public List<String> getFlightTypes() {
        return flightTypes;
    }

    public void setFlightTypes(List<String> flightTypes) {
        this.flightTypes = flightTypes;
    }

    public String getSpecificAirline() {
        return specificAirline;
    }

    public void setSpecificAirline(String specificAirline) {
        this.specificAirline = specificAirline;
    }

    public String getSpecificFlight() {
        return specificFlight;
    }

    public void setSpecificFlight(String specificFlight) {
        this.specificFlight = specificFlight;
    }
}
