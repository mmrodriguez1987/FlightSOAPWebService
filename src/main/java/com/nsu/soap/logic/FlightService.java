package com.nsu.soap.logic;

import com.nsu.soap.dao.FlightDAO;
import com.nsu.soap.model.Flight;
import java.util.List;

/**
 * Service class for managing flight operations.
 * Provides methods to create, retrieve, update, and delete flights.
 */
public class FlightService {


    private final FlightDAO dao = new FlightDAO();

    /**
     * Creates a new flight.
     *
     * @param flight the flight to create
     * @return the created flight with generated ID
     */
    public Flight createFlight(Flight flight) {
        return dao.create(flight);
    }

    /**
     * Retrieves a flight by its ID.
     *
     * @param id the ID of the flight to retrieve
     * @return the flight with the specified ID, or null if not found
     */
    public Flight getFlight(Long id) {
        return dao.findById(id);
    }

    /**
     * Lists all flights.
     *
     * @return a list of all flights
     */
    public List<Flight> listFlights() {
        return dao.findAll();
    }

    /**
     * Updates an existing flight.
     *
     * @param flight the flight with updated information
     * @return the updated flight
     */
    public Flight updateFlight(Flight flight) {
        return dao.update(flight);
    }

    /**
     * Deletes a flight by its ID.
     *
     * @param id the ID of the flight to delete
     * @return true if the flight was deleted, false otherwise
     */
    public boolean deleteFlight(Long id) {
        return dao.delete(id);
    }
}
