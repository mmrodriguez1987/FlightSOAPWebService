/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.flight.service;

import com.example.flight.model.FlightAvailabilityRequestType;   // ← use this
import com.example.flight.response.FlightAvailabilityResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(
    targetNamespace = "http://example.com/flight",
    name = "FlightService"
)
public interface FlightService {

    @WebMethod(operationName = "SearchFlights")
    @WebResult(name = "FlightAvailabilityResponse", 
               targetNamespace = "http://example.com/flight")
    FlightAvailabilityResponse searchFlights(
        @WebParam(name = "FlightAvailabilityRequest", 
                  targetNamespace = "http://example.com/flight")
        FlightAvailabilityRequestType request   // ← note the “Type” suffix here
    );
}
