package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.service.model.Tour;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import at.fhtw.swen2.tutorial.service.CsvExporterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CsvExporterServiceImpl implements CsvExporterService {

    private static final String CSV_SEPARATOR = ";";

    public void export(File file, List<Tour> tourList) {
        try (FileWriter writer = new FileWriter(file)) {
            // Write header row
            writer.append("Name");
            writer.append(CSV_SEPARATOR);
            writer.append("Description");
            writer.append(CSV_SEPARATOR);
            writer.append("From");
            writer.append(CSV_SEPARATOR);
            writer.append("To");
            writer.append(CSV_SEPARATOR);
            writer.append("Transport Type");
            writer.append(CSV_SEPARATOR);
            writer.append("Distance");
            writer.append(CSV_SEPARATOR);
            writer.append("Estimated Time");
            writer.append(CSV_SEPARATOR);

            writer.append('\n');

            // Write tour data rows
            for (Tour tour : tourList) {
                writer.append(tour.getName());
                writer.append(CSV_SEPARATOR);
                writer.append(tour.getDescription());
                writer.append(CSV_SEPARATOR);
                writer.append(tour.getFrom());
                writer.append(CSV_SEPARATOR);
                writer.append(tour.getTo());
                writer.append(CSV_SEPARATOR);
                writer.append(String.valueOf(tour.getTransportType()));
                writer.append(CSV_SEPARATOR);
                writer.append(Float.toString(tour.getDistance()));
                writer.append(CSV_SEPARATOR);
                writer.append(Float.toString(tour.getEstimatedTime()));
                writer.append('\n');
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
