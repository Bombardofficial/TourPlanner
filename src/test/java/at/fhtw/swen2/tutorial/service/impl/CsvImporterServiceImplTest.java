package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvImporterServiceImplTest {

    @Test
    public void testIsValidHeader_ValidHeader() {
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
/*
    @Test
    public void testImportFile_ValidCSVFile() {
        // Arrange
        CsvImporterServiceImpl importer = new CsvImporterServiceImpl();
        File csvFile = new File("valid.csv");

        // Mock MapQuestService
        MapQuestService mapQuestService = Mockito.mock(MapQuestService.class);
        Mockito.when(mapQuestService.getMapByteArray(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new byte[0]);
        importer.mapQuestService = mapQuestService;

        // Act
        List<Tour> tourList;
        try {
            tourList = importer.importFile(csvFile);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
            return;
        }

        // Assert
        assertEquals(2, ((List<h.csv>) tourList).size());
        assertEquals("Jovana's tour", tourList.get(0).getName());
        assertEquals("nice view", tourList.get(0).getDescription());
        assertEquals("Vienna", tourList.get(0).getFrom());
        assertEquals("Belgrade", tourList.get(0).getTo());
        assertEquals(TransportType.CAR, tourList.get(0).getTransportType());
        assertEquals(150f, tourList.get(0).getDistance(), 0.001f);
        assertEquals(6f, tourList.get(0).getEstimatedTime(), 0.001f);

        assertEquals("Botond's tour", tourList.get(1).getName());
        assertEquals("no mountains in Hungary", tourList.get(1).getDescription());
        assertEquals("Belgrade", tourList.get(1).getFrom());
        assertEquals("Budapest", tourList.get(1).getTo());
        assertEquals(null, tourList.get(1).getTransportType());
        assertEquals(100f, tourList.get(1).getDistance(), 0.001f);
        assertEquals(4f, tourList.get(1).getEstimatedTime(), 0.001f);
    }

    @Test
    public void testImportFile_InvalidCSVFile_InvalidFieldCount() {
        // Arrange
        CsvImporterServiceImpl importer = new CsvImporterServiceImpl();
        File csvFile = new File("invalid_field_count.csv");

        // Act and Assert
        assertThrows(Exception.class, () -> importer.importFile(csvFile));
    }

    @Test
    public void testImportFile_InvalidCSVFile_InvalidHeader() {
        // Arrange
        CsvImporterServiceImpl importer = new CsvImporterServiceImpl();
        File csvFile = new File("invalid_header.csv");

        // Act and Assert
        assertThrows(Exception.class, () -> importer.importFile(csvFile));
    }
*/
}