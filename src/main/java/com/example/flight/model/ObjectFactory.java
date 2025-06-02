package com.example.flight.model;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public FlightAvailabilityRequestType createFlightAvailabilityRequestType() {
        return new FlightAvailabilityRequestType();
    }

    public FlightAvailabilityRequestType.Passengers createFlightAvailabilityRequestTypePassengers() {
        return new FlightAvailabilityRequestType.Passengers();
    }

    public PassengerCountType createPassengerCountType() {
        return new PassengerCountType();
    }
}