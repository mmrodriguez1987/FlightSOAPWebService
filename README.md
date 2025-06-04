# Flight SOAP Web Service

[![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.mojo/jaxb2-maven-plugin.svg?label=Maven%20Central)](https://search.maven.org/artifact/org.codehaus.mojo/jaxb2-maven-plugin)
[![Build Status](https://github.com/mojohaus/jaxb2-maven-plugin/actions/workflows/maven.yml/badge.svg)](https://github.com/mojohaus/jaxb2-maven-plugin/actions/workflows/maven.yml)

A Java-based SOAP Web Service for managing flight information based on the flight.xsd schema. This project is part of an assignment for the MSIT665 Web Services class at Nova Southeastern University.

## Overview

The Flight SOAP Web Service provides a comprehensive API for flight management operations, including registering new flights, retrieving flight details, and deleting flights from the system.

## Features

- Register new flights with passenger details
- Retrieve flight information by ID
- Get a list of all available flight IDs
- Delete flights from the system

## Service Endpoints

### WSDL Location
The WSDL is available at: `/services/flightService?wsdl`

### Endpoint URL
```
http://[host]:[port]/FlightSOAPWebService/services/flightService
```

### Available Operations

#### registerFlight
Register a new flight in the system
- **Input:** FlightAvailabilityRequestType
- **Output:** String (flightId)

**Sample Request:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://example.com/flight/ws" xmlns:flig="http://example.com/flight">
    <soapenv:Header/>
    <soapenv:Body>
        <ws:registerFlight>
            <!--Optional:-->
            <flightRequest>
                <flig:Origin>JFK</flig:Origin>
                <flig:Destination>LAX</flig:Destination>
                <flig:DepartureDate>2024-08-30</flig:DepartureDate>
                <flig:Passengers>
                    <!--1 or more repetitions:-->
                    <flig:Passenger Type="ADT" Quantity="2"/>
                </flig:Passengers>
                <!--Optional:-->
                <flig:TimeRange>
                    <flig:StartTime>07:00</flig:StartTime>
                    <flig:EndTime>12:00</flig:EndTime>
                </flig:TimeRange>
                <!--Optional:-->
                <flig:PreferredConnectingCities>
                    <!--1 or more repetitions:-->
                    <flig:ConnectingCity>ATL</flig:ConnectingCity>
                </flig:PreferredConnectingCities>
                <!--Optional:-->
                <flig:PreferredAirlines>
                    <!--1 or more repetitions:-->
                    <flig:AirlineCode>DL</flig:AirlineCode>
                </flig:PreferredAirlines>
                <!--Optional:-->
                <flig:FlightTypes>
                    <!--1 or more repetitions:-->
                    <flig:FlightType>nonstop</flig:FlightType>
                </flig:FlightTypes>
                <!--Optional:-->
                <flig:SpecificAirline>UA</flig:SpecificAirline>
                <!--Optional:-->
                <flig:SpecificFlight>
                    <flig:AirlineCode>UA</flig:AirlineCode>
                    <flig:FlightNumber>UA100</flig:FlightNumber>
                    <flig:FlightDate>2024-08-30</flig:FlightDate>
                    <!--Optional:-->
                    <flig:BookingClass>OT</flig:BookingClass>
                </flig:SpecificFlight>
            </flightRequest>
        </ws:registerFlight>
    </soapenv:Body>
```

#### getFlight
Retrieve flight details by ID
- **Input:** String (flightId)
- **Output:** FlightAvailabilityRequestType

**Sample Request:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://example.com/flight/ws">
    <soapenv:Header/>
    <soapenv:Body>
        <ws:getFlight>
            <!--Optional:-->
            <flightId>fb5f6107-9ce8-4ada-a176-68d9c5417b54</flightId>
        </ws:getFlight>
    </soapenv:Body>
</soapenv:Envelope>
```

#### getAllFlightIds
Get a list of all flight IDs in the system
- **Input:** None
- **Output:** List of Strings (flightIds)

**Sample Request:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://example.com/flight/ws">
    <soapenv:Header/>
    <soapenv:Body>
        <ws:getAllFlightIds/>
    </soapenv:Body>
</soapenv:Envelope>
```

#### deleteFlight
Delete a flight from the system
- **Input:** String (flightId)
- **Output:** None

**Sample Request:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:flig="http://example.com/flight/ws">
   <soapenv:Header/>
   <soapenv:Body>
      <flig:deleteFlightRequest>
         <flightId>FL-12345</flightId>
      </flig:deleteFlightRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

## Getting Started

To use this service, you can:
- Use a SOAP client like SoapUI or Postman
- Generate client code using the WSDL with tools like wsimport, Apache CXF, or Spring-WS
- Use the provided Java client in `nsu.msit665.flight.client.FlightServiceClient`

### Java Client Example

```java
FlightServiceClient client = new FlightServiceClient();
FlightAvailabilityRequestType request = client.createSampleFlightRequest();
String flightId = client.registerFlight(request);
System.out.println("Flight registered with ID: " + flightId);
```

## Technical Stack

### Java Version
- Java 17

### Build Tool
- Maven 3.8+

### Libraries and Dependencies
- Jakarta XML Web Services API (jakarta.xml.ws-api)
- Jakarta SOAP with Attachments API (jakarta.xml.soap-api)
- Jakarta XML Binding API (jakarta.xml.bind-api)
- Glassfish Jakarta EE Implementation (glassfish.jaxb.runtime)
- Jakarta Annotations API (jakarta.annotation-api)
- Jakarta Servlet API (jakarta.servlet-api)

### Maven Plugins
- JAXB2 Maven Plugin (org.codehaus.mojo:jaxb2-maven-plugin)
- Maven Compiler Plugin
- Maven War Plugin
- Maven Resources Plugin

## Developer Information

This project was developed by Marcos Rodriguez as part of the MSIT665 Web Services class at Nova Southeastern University.

### Contact Information
- **LinkedIn:** [https://www.linkedin.com/in/mmrodriguez1987/](https://www.linkedin.com/in/mmrodriguez1987/)
- **Website:** [https://mrkdev.pro/](https://mrkdev.pro/)
- **GitHub:** [https://github.com/mmrodriguez1987](https://github.com/mmrodriguez1987)
- **Instagram:** [https://www.instagram.com/mrkdev.pro/](https://www.instagram.com/mrkdev.pro/)

## License

Copyright © 2024 Marcos Rodriguez. All rights reserved.

## Source Code Repository

Source code is available at [GitHub - FlightSOAPWebService](https://github.com/mmrodriguez1987/FlightSOAPWebService/tree/main)