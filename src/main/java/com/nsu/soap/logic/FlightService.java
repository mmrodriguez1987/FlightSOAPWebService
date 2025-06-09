package com.nsu.soap.logic;

import com.nsu.soap.dao.FlightDAO;
import com.nsu.soap.model.Flight;
import java.util.List;

public class FlightService {

    private final FlightDAO dao = new FlightDAO();

    public Flight createFlight(Flight flight) {
        // validate fields as needed...
        return dao.create(flight);
    }

    public Flight getFlight(Long id) {
        return dao.findById(id);
    }

    public List<Flight> listFlights() {
        return dao.findAll();
    }

    public Flight updateFlight(Flight flight) {
        return dao.update(flight);
    }

    public boolean deleteFlight(Long id) {
        return dao.delete(id);
    }
}
