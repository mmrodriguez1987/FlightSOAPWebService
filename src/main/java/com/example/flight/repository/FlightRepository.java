package com.example.flight.repository;

import com.example.flight.model.FlightAvailabilityRequestType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightRepository {
    private static final String STORAGE_DIR = System.getProperty("catalina.base", System.getProperty("user.home")) + "/flightdata";
    
    public FlightRepository() {
        // Ensure storage directory exists
        File storageDir = new File(STORAGE_DIR);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
    }
    
    public String saveFlight(FlightAvailabilityRequestType flight) throws JAXBException {
        // Generate a unique ID for the flight
        String flightId = UUID.randomUUID().toString();
        
        // Create JAXB context and marshaller
        JAXBContext context = JAXBContext.newInstance(FlightAvailabilityRequestType.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        // Create file with the flight ID
        File file = new File(STORAGE_DIR + "/" + flightId + ".xml");
        
        // Marshal the flight object to XML
        marshaller.marshal(flight, file);
        
        return flightId;
    }
    
    public FlightAvailabilityRequestType getFlight(String flightId) throws JAXBException, IOException {
        File file = new File(STORAGE_DIR + "/" + flightId + ".xml");
        if (!file.exists()) {
            return null;
        }
        
        JAXBContext context = JAXBContext.newInstance(FlightAvailabilityRequestType.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        
        try (FileReader reader = new FileReader(file)) {
            return (FlightAvailabilityRequestType) unmarshaller.unmarshal(reader);
        }
    }
    
    public List<String> getAllFlightIds() throws IOException {
        List<String> flightIds = new ArrayList<>();
        
        Path dir = Paths.get(STORAGE_DIR);
        if (Files.exists(dir)) {
            try (Stream<Path> paths = Files.list(dir)) {
                flightIds = paths
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .filter(name -> name.endsWith(".xml"))
                    .map(name -> name.substring(0, name.length() - 4))
                    .collect(Collectors.toList());
            }
        }
        
        return flightIds;
    }
    
    public void deleteFlight(String flightId) {
        File file = new File(STORAGE_DIR + "/" + flightId + ".xml");
        if (file.exists()) {
            file.delete();
        }
    }
}
