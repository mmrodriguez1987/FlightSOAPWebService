package com.nsu.soap.service;

import com.nsu.soap.model.Flight;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import java.util.List;


@WebService(serviceName = "FlightServiceSOAP", targetNamespace = "http://soap.nsu.com/flights")
public interface FlightEndpoint {

    @WebMethod(operationName = "createFlight")
    @WebResult(name = "newFlight")
    Flight createFlight(@WebParam(name = "flight") Flight flight);

    @WebMethod(operationName = "getFlight")
    @WebResult(name = "flight")
    Flight getFlight(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "listFlights")
    @WebResult(name = "flights")
    List<Flight> listFlights();

    @WebMethod(operationName = "updateFlight")
    @WebResult(name = "updatedFlight")
    Flight updateFlight(@WebParam(name = "flight") Flight flight);

    @WebMethod(operationName = "deleteFlight")
    @WebResult(name = "result")
    boolean deleteFlight(@WebParam(name = "id") Long id);
}
