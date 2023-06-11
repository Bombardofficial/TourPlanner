package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.service.CsvImporterService;

import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CsvImporterServiceImpl implements CsvImporterService {

    private static final String CSV_SEPARATOR = ";";

    @Autowired
    public MapQuestService mapQuestService;

    public List<Tour> importFile(File file) throws Exception {
        List<Tour> tourList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read header row and validate
            String headerLine = reader.readLine();
            if (!isValidHeader(headerLine)) {
                throw new Exception("Invalid CSV header");
            }

            // Read data rows
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(CSV_SEPARATOR);

                // Validate number of fields
                if (fields.length != 7) {
                    throw new Exception("Invalid number of fields in CSV row");
                }

                // Parse fields into Tour object
                Tour tour = Tour.builder()
                        .name(fields[0])
                        .description(fields[1])
                        .from(fields[2])
                        .to(fields[3])
                        .transportType(TransportType.valueOf(fields[4]))
                        .distance(Float.parseFloat(fields[5]))
                        .estimatedTime(Float.parseFloat(fields[6]))
                        .build();

                tourList.add(tour);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new Exception("Error reading or parsing CSV file", e);
        }

        return tourList;
    }

    public boolean isValidHeader(String headerLine) {
        String[] fields = headerLine.split(CSV_SEPARATOR);

        // Validate number of fields
        if (fields.length != 7) {
            return false;
        }

        // Validate field names
        if (!fields[0].equals("Name") || !fields[1].equals("Description") || !fields[2].equals("From")
                || !fields[3].equals("To") || !fields[4].equals("Transport Type") || !fields[5].equals("Distance")
                || !fields[6].equals("Estimated Time")) {
            return false;
        }

        return true;
    }
}
