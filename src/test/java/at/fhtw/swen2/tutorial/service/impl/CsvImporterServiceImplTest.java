package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
/*
    @Mock
    private CsvImporterServiceImpl csvImporterServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        csvImporterServiceImpl = new CsvImporterServiceImpl();
    }

    @Test
    void testPerformImport() throws Exception {
        // Arrange
        List<Tour> expectedTours = new ArrayList<>();
        Tour tour1 = Tour.builder()
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .build();

        Tour tour2 = Tour.builder()
                .name("Botond's tour")
                .description("no mountains in Hungary")
                .from("Belgrade")
                .to("Budapest")
                .distance(100)
                .estimatedTime(4)
                .build();

        expectedTours.add(tour1);
        expectedTours.add(tour2);

        // Create a temporary CSV file
        File tempFile = File.createTempFile("testfile", ".csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("Name;Description;From;To;Transport Type;Distance;Estimated Time\n");
        writer.write("Jovana's tour;nice view;Vienna;Belgrade;CAR;150;6\n");
        writer.write("Botond's tour;no mountains in Hungary;Belgrade;Budapest;BUS;100;4\n");
        writer.close();

        // Act
        List<Tour> importedTours = csvImporterServiceImpl.importFile(tempFile);

        // Assert
        assertEquals(expectedTours.size(), importedTours.size());
        assertEquals(expectedTours, importedTours);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        CsvImporterServiceImpl csvImporterServiceImpl = new CsvImporterServiceImpl();
    }
    @Test
    void testPerformImport() throws Exception {
        // Arrange
        List<Tour> myTours = new ArrayList<>();
        Tour tour1 = Tour.builder()
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .build();

        Tour tour2 = Tour.builder()
                .name("Botond's tour")
                .description("no mountains in Hungary")
                .from("Belgrade")
                .to("Budapest")
                .distance(100)
                .estimatedTime(4)
                .build();

        myTours.add(tour1);
        myTours.add(tour2);
        CsvImporterServiceImpl csvImporterServiceImpl = new CsvImporterServiceImpl();
        File file = new File("Jovi");

        // Act
        List<Tour> impoTours = csvImporterServiceImpl.importFile(file);

        // Assert
      //  assertEquals(myTours.size(), impoTours.size());
        assertEquals(myTours, impoTours );
       // assertNotEquals(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1970"), impoTours .get(0).getTourLogs().get(0).getDateTime());
    }
*/
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

}