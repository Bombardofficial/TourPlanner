package at.fhtw.swen2.tutorial.service;

import at.fhtw.swen2.tutorial.service.model.Tour;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ExporterService {
    void exportCSV(File file, List<Tour> tourList);
    void exportPDF(File file, List<Tour> tourList) throws IOException;
    void exportOne(File file, Tour tour) throws IOException;
}
