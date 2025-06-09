package com.nsu.soap.service;

import com.nsu.soap.logic.FlightService;
import com.nsu.soap.model.Flight;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;

@WebService(endpointInterface = "com.nsu.soap.service.FlightEndpoint",
        serviceName = "FlightServiceSOAP",
        targetNamespace = "http://soap.nsu.com/flights")
public class FlightEndpointImpl implements FlightEndpoint {

    private final FlightService flightService = new FlightService();

    @Override
    public Flight createFlight(Flight flight) {
        // 1) persist to DB
        Flight created = flightService.createFlight(flight);

        // 2) generate XML file
        try {
            JAXBContext context = JAXBContext.newInstance(Flight.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            String tomcatBase = System.getProperty("catalina.base");
            String targetDir = tomcatBase + File.separator +
                    "webapps" + File.separator +
                    "FlightSOAPWebService" + File.separator +
                    "data" + File.separator + "xml";

            File folder = new File(targetDir);
            if (!folder.exists()) folder.mkdirs();

            File xmlFile = new File(folder, "flight-" + created.getId() + ".xml");
            marshaller.marshal(created, xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
            // you may throw a WebServiceException here
        }

        return created;
    }

    @Override
    public Flight getFlight(Long id) {
        return flightService.getFlight(id);
    }

    @Override
    public java.util.List<Flight> listFlights() {
        return flightService.listFlights();
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightService.updateFlight(flight);
    }

    @Override
    public boolean deleteFlight(Long id) {
        return flightService.deleteFlight(id);
    }
}
