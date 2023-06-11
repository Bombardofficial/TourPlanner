package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvImporterServiceImplTest {


    @Test
    public void testIsValidHeader() {
        // Arrange
        String validHeader = "Name;Description;From;To;Transport Type;Distance;Estimated Time";

        // Act
       CsvImporterServiceImpl importer = new  CsvImporterServiceImpl();
        boolean isValid = importer.isValidHeader(validHeader);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testInvalidHeaderBecauseMissingField() {
        // Arrange
        String invalidHeader = "Name;Description;From;To;Transport Type;Distance";

        // Act
        CsvImporterServiceImpl importer = new  CsvImporterServiceImpl();
        boolean isValid = importer.isValidHeader(invalidHeader);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testInvalidHeaderBecauseWrongName() {
        // Arrange
        String invalidHeader = "Name;Description;From;To;Transport Type;Distance; Jovana";

        // Act
        CsvImporterServiceImpl importer = new  CsvImporterServiceImpl();
        boolean isValid = importer.isValidHeader(invalidHeader);

        // Assert
        assertFalse(isValid);

    }

    @Test
    public void testImportFile_ThrowsException() {
        // Arrange
        CsvImporterServiceImpl importer = new CsvImporterServiceImpl();
        File csvFile = new File("invalid_field_count.csv");

        // Act and Assert
        assertThrows(Exception.class, () -> importer.importFile(csvFile));
    }


}