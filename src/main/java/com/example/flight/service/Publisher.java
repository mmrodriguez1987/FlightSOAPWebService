/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.flight.service;

import javax.xml.ws.Endpoint;

/**
 * Standalone publisher for the FlightService.
 */
public class Publisher {
    public static void main(String[] args) {
        String url = "http://localhost:8080/flights";
        System.out.println("Publishing FlightService at " + url + "?wsdl");
        Endpoint.publish(url, new FlightServiceImpl());
        System.out.println("Service published. CTRL+C to stop.");
    }
}
