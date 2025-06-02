package com.example.flight.service;

import com.example.flight.model.FlightAvailabilityRequestType;
import com.example.flight.repository.FlightRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;

@WebService(serviceName = "FlightService", targetNamespace = "http://example.com/flight/ws")
public class FlightService {

    private FlightRepository repository = new FlightRepository();

    @WebMethod(operationName = "registerFlight")
    @WebResult(name = "flightId")
    public String registerFlight(@WebParam(name = "flightRequest") FlightAvailabilityRequestType request) {
        try {
            return repository.saveFlight(request);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to register flight: " + e.getMessage(), e);
        }
    }

    @WebMethod(operationName = "getFlight")
    @WebResult(name = "com/example/flight")
    public FlightAvailabilityRequestType getFlight(@WebParam(name = "flightId") String flightId) {
        try {
            return repository.getFlight(flightId);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Failed to get flight: " + e.getMessage(), e);
        }
    }
    
    @WebMethod(operationName = "getAllFlightIds")
    @WebResult(name = "flightIds")
    public List<String> getAllFlightIds() {
        try {
            return repository.getAllFlightIds();
        } catch (IOException e) {
            throw new RuntimeException("Failed to get flight IDs: " + e.getMessage(), e);
        }
    }
    
    @WebMethod(operationName = "deleteFlight")
    public void deleteFlight(@WebParam(name = "flightId") String flightId) {
        repository.deleteFlight(flightId);
    }
}
