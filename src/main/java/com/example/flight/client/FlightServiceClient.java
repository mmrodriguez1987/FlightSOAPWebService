package com.example.flight.client;

import com.example.flight.model.*;
import com.example.flight.service.FlightService;
import jakarta.xml.ws.Service;

import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

/**
 * A simple client to test the Flight SOAP Service.
 * Note: This is for local testing only and won't be deployed.
 */
public class FlightServiceClient {

    public static void main(String[] args) throws Exception {
        // Service endpoint URL - adjust if needed
        URL url = new URL("http://localhost:8080/FlightSOAPWebService/services/flightService?wsdl");
        
        // QName for the service
        QName qname = new QName("http://example.com/flight/ws", "FlightService");
        
        // Create a service instance
        Service service = Service.create(url, qname);
        
        // Get the port (proxy)
        FlightService flightService =
            service.getPort(FlightService.class);
        
        // Create a sample flight request
        FlightAvailabilityRequestType request = createSampleFlightRequest();
        
        // Register the flight
        String flightId = flightService.registerFlight(request);
        System.out.println("Flight registered with ID: " + flightId);
        
        // Get all flight IDs
        List<String> allIds = flightService.getAllFlightIds();
        System.out.println("All flight IDs: " + allIds);
        
        // Retrieve the flight
        FlightAvailabilityRequestType retrievedFlight = flightService.getFlight(flightId);
        System.out.println("Retrieved flight: " + retrievedFlight.getOrigin() + " to " + 
                          retrievedFlight.getDestination());
        
        // Delete the flight
        flightService.deleteFlight(flightId);
        System.out.println("Flight deleted");
        
        // Verify deletion
        allIds = flightService.getAllFlightIds();
        System.out.println("All flight IDs after deletion: " + allIds);
    }
    
    private static FlightAvailabilityRequestType createSampleFlightRequest() {
        ObjectFactory factory = new ObjectFactory();
        
        // Create the main request
        FlightAvailabilityRequestType request = factory.createFlightAvailabilityRequestType();
        
        // Set origin and destination
        request.setOrigin("JFK");
        request.setDestination("LAX");
        
        // Set departure date (adjust to future date)
        request.setDepartureDate(LocalDate.now().plusDays(30).toString());
        
        // Create passengers section
        FlightAvailabilityRequestType.Passengers passengers = factory.createFlightAvailabilityRequestTypePassengers();
        
        // Add an adult passenger
        PassengerCountType adultPassenger = factory.createPassengerCountType();
        adultPassenger.setType("ADT");
        adultPassenger.setQuantity(BigInteger.valueOf(1));
        passengers.getPassenger().add(adultPassenger);
        
        // Add the passengers to the request
        request.setPassengers(passengers);
        
        return request;
    }
}
