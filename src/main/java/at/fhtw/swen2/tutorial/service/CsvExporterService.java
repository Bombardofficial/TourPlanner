package at.fhtw.swen2.tutorial.service;

import at.fhtw.swen2.tutorial.service.model.Tour;

import java.io.File;
import java.util.List;

public interface CsvExporterService {
    void export(File file, List<Tour> tourList);
}
