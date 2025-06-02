/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.flight.service;

import com.example.flight.model.FlightAvailabilityRequestType;
import com.example.flight.model.PassengerCountType;
import com.example.flight.response.FlightAvailabilityResponse;
import com.example.flight.response.FlightInfo;

import javax.jws.WebService;
import java.time.LocalTime;
import java.util.List;

@WebService(
    endpointInterface = "com.example.flight.service.FlightService",
    targetNamespace   = "http://example.com/flight",
    serviceName       = "FlightService"
)
public class FlightServiceImpl implements FlightService {

    @Override
    public FlightAvailabilityResponse searchFlights(
            FlightAvailabilityRequestType request) {
        FlightAvailabilityResponse response = new FlightAvailabilityResponse();

        // Now “request.getOrigin()” etc. still work exactly as before,
        // because those getters were generated inside FlightAvailabilityRequestType.
        String origin = request.getOrigin();
        String dest   = request.getDestination();

        int totalPassengers = 0;
        if (request.getPassengers() != null) {
            List<PassengerCountType> paxList = 
                request.getPassengers().getPassenger();
            for (PassengerCountType pax : paxList) {
                totalPassengers += pax.getQuantity().intValue();
            }
        }

        if (origin != null && !origin.isEmpty()
            && dest != null && !dest.isEmpty()
            && totalPassengers > 0) {
            // … same dummy‐flight logic as before …
            FlightInfo f1 = new FlightInfo(
                "AA", "100",
                LocalTime.of(8, 30),
                LocalTime.of(11, 15),
                199.99
            );
            FlightInfo f2 = new FlightInfo(
                "DL", "245",
                LocalTime.of(13, 0),
                LocalTime.of(15, 45),
                219.50
            );
            response.addFlight(f1);
            response.addFlight(f2);
        }
        return response;
    }
}
