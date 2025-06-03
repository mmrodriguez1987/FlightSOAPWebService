package nsu.msit665.flight.client;

import nsu.msit665.flight.model.*;
import nsu.msit665.flight.service.FlightService;
import jakarta.xml.ws.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;

public class FlightServiceClient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/FlightSOAPWebService/services/flightService?wsdl");
        QName qname = new QName("http://example.com/flight/ws", "FlightService");
        Service service = Service.create(url, qname);
        FlightService flightService = service.getPort(FlightService.class);

        FlightAvailabilityRequestType request = createSampleFlightRequest();
        String flightId = flightService.registerFlight(request);
        System.out.println("Flight registered with ID: " + flightId);

        List<String> allIds = flightService.getAllFlightIds();
        System.out.println("All flight IDs: " + allIds);

        FlightAvailabilityRequestType retrievedFlight = flightService.getFlight(flightId);
        System.out.println("Retrieved flight: " + retrievedFlight.getOrigin() + " to " +
                          retrievedFlight.getDestination());

        flightService.deleteFlight(flightId);
        System.out.println("Flight deleted");

        allIds = flightService.getAllFlightIds();
        System.out.println("All flight IDs after deletion: " + allIds);
    }

    private static FlightAvailabilityRequestType createSampleFlightRequest() throws Exception {
        ObjectFactory factory = new ObjectFactory();

        FlightAvailabilityRequestType request = factory.createFlightAvailabilityRequestType();

        request.setSpecificFlight(factory.createSpecificFlightType());
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setPreferredAirlines(factory.createFlightAvailabilityRequestTypePreferredAirlines());

        // Convert LocalDate to XMLGregorianCalendar
        LocalDate departureDate = LocalDate.now().plusDays(30);
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.set(departureDate.getYear(), departureDate.getMonthValue() - 1, departureDate.getDayOfMonth());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        request.setDepartureDate(xmlDate);



        // Create passengers
        FlightAvailabilityRequestType.Passengers passengers = factory.createFlightAvailabilityRequestTypePassengers();

        // Add adult passenger
        PassengerCountType adultPassenger = factory.createPassengerCountType();
        adultPassenger.setType(PassengerTypeEnum.ADT);
        adultPassenger.setQuantity(BigInteger.valueOf(2));
        passengers.getPassenger().add(adultPassenger);

        // Add child passenger
        PassengerCountType childPassenger = factory.createPassengerCountType();
        childPassenger.setType(PassengerTypeEnum.CHD);
        childPassenger.setQuantity(BigInteger.valueOf(1));
        passengers.getPassenger().add(childPassenger);

        request.setPassengers(passengers);
        return request;
    }
}