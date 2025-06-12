package com.nsu.soap.dao;

import com.nsu.soap.model.Flight;
import com.nsu.soap.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * DAO class for managing Flight entities in the database.
 * Provides methods to create, read, update, and delete flights.
 */
public class FlightDAO {

    /**
     * Creates a new flight record in the database.
     *
     * @param flight the flight to create
     * @return the created flight with generated ID
     */
    public Flight create(Flight flight) {
        EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
            return flight;
        } finally {
            em.close();
        }
    }

    /**
     * Finds a flight by its ID, fetching all associated collections eagerly.
     *
     * @param id the ID of the flight to find
     * @return the flight with all associations initialized
     */
    public Flight findById(Long id) {
        EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            // 1. fetch preferredConnectingCities
            Flight flight = em.createQuery(
                            "SELECT f FROM Flight f LEFT JOIN FETCH f.preferredConnectingCities WHERE f.id = :id",
                            Flight.class)
                    .setParameter("id", id)
                    .getSingleResult();

            // 2. fetch preferredAirlines
            em.createQuery(
                            "SELECT f FROM Flight f LEFT JOIN FETCH f.preferredAirlines WHERE f.id = :id",
                            Flight.class)
                    .setParameter("id", id)
                    .getSingleResult();

            // 3. fetch flightTypes
            em.createQuery(
                            "SELECT f FROM Flight f LEFT JOIN FETCH f.flightTypes WHERE f.id = :id",
                            Flight.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return flight;
        } finally {
            em.close();
        }
    }

    /**
     * Finds all flights in the database, fetching all associated collections eagerly.
     *
     * @return a list of all flights with all associations initialized
     */
    public List<Flight> findAll() {
        EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            // 1. fetch preferredConnectingCities
            List<Flight> flights = em.createQuery(
                    "SELECT DISTINCT f FROM Flight f " +
                            "LEFT JOIN FETCH f.preferredConnectingCities", Flight.class
            ).getResultList();

            // index by ID to merge later results
            Map<Long, Flight> map = flights.stream()
                    .collect(Collectors.toMap(Flight::getId, Function.identity()));

            // 2. fetch preferredAirlines for same flights
            em.createQuery(
                            "SELECT f FROM Flight f " +
                                    "LEFT JOIN FETCH f.preferredAirlines " +
                                    "WHERE f.id IN :ids", Flight.class
                    )
                    .setParameter("ids", map.keySet())
                    .getResultList();

            // 3. fetch flightTypes for same flights
            em.createQuery(
                            "SELECT f FROM Flight f " +
                                    "LEFT JOIN FETCH f.flightTypes " +
                                    "WHERE f.id IN :ids", Flight.class
                    )
                    .setParameter("ids", map.keySet())
                    .getResultList();

            // return all flights with all associations initialized
            return new ArrayList<>(map.values());
        } finally {
            em.close();
        }
    }

    /**
     * Updates an existing flight record in the database.
     *
     * @param flight the flight with updated information
     * @return the updated flight
     */
    public Flight update(Flight flight) {
        EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Flight existing = em.find(Flight.class, flight.getId());
            if (existing != null) {
                existing.setOrigin(flight.getOrigin());
                existing.setDestination(flight.getDestination());
                existing.setDepartureDate(flight.getDepartureDate());
                existing.setPassengers(flight.getPassengers());
                existing.setTimeRange(flight.getTimeRange());
                existing.setPreferredConnectingCities(flight.getPreferredConnectingCities());
                existing.setPreferredAirlines(flight.getPreferredAirlines());
                existing.setFlightTypes(flight.getFlightTypes());
                existing.setSpecificAirline(flight.getSpecificAirline());
                existing.setSpecificFlight(flight.getSpecificFlight());
                em.merge(existing);
            }
            em.getTransaction().commit();
            return existing;
        } finally {
            em.close();
        }
    }

    /**
     * Deletes a flight record from the database by its ID.
     *
     * @param id the ID of the flight to delete
     * @return true if the flight was deleted, false if it was not found
     */
    public boolean delete(Long id) {
        EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Flight f = em.find(Flight.class, id);
            if (f != null) {
                em.remove(f);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } finally {
            em.close();
        }
    }
}
