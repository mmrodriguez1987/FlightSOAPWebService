package com.nsu.soap.dao;

import com.nsu.soap.model.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.nsu.soap.util.EnvConfig;

public class FlightDAO {

    private static final Map<String, Object> props = new HashMap<>();

    static {
        props.put("jakarta.persistence.jdbc.user", EnvConfig.get("db.user"));
        props.put("jakarta.persistence.jdbc.password", EnvConfig.get("db.password"));
        props.put("jakarta.persistence.jdbc.url", EnvConfig.get("db.url"));
    }

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("FlightSOAPWebServicePU", props);

    public Flight create(Flight flight) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
            return flight;
        } finally {
            em.close();
        }
    }

    public Flight findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Flight.class, id);
        } finally {
            em.close();
        }
    }

    public List<Flight> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String hql = "SELECT f FROM Flight f";
            TypedQuery<Flight> query = em.createQuery(hql, Flight.class);
            List<Flight> flights = query.getResultList();
            return flights;
        } finally {
            em.close();
        }
    }

    public Flight update(Flight flight) {
        EntityManager em = emf.createEntityManager();
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

    public boolean delete(Long id) {
        EntityManager em = emf.createEntityManager();
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
