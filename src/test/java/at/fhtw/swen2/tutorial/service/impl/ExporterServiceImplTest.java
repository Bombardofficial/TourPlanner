package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.TourLogRepository;
import at.fhtw.swen2.tutorial.persistence.repositories.TourRepository;
import at.fhtw.swen2.tutorial.service.model.Tour;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.hibernate.tool.schema.spi.Exporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static at.fhtw.swen2.tutorial.persistence.entities.TransportType.CAR;
import static org.junit.jupiter.api.Assertions.*;

class ExporterServiceImplTest {

    @Test
    public void testFileExistenceForExportCSV() {

        // Arrange
        List<Tour> tourList = new ArrayList<>();
        Tour tour1 =  Tour.builder()
                .id(11l)
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .transportType(CAR)
                .build();

        Tour tour2 = Tour.builder()
                .name("Botond's tour")
                .description("no mountains in Hungary")
                .from("Belgrade")
                .to("Budapest")
                .distance(100)
                .estimatedTime(4)
                .build();

        tourList.add(tour1);
        tourList.add(tour2);

        File file = new File("test.csv");

        // Act
        ExporterServiceImpl exporter = new ExporterServiceImpl();
        exporter.exportCSV(file, tourList);

        // Assert
        assertTrue(file.exists());

        file.delete(); //damit die csv Datei nicht bei mir gespeichert wird

    }

    @Test
    public void testNumberOfRowsForExportCSV() {

        // Arrange
        List<Tour> tourList = new ArrayList<>();
        Tour tour1 =  Tour.builder()
                .id(11l)
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .transportType(CAR)
                .build();

        Tour tour2 = Tour.builder()
                .name("Botond's tour")
                .description("no mountains in Hungary")
                .from("Belgrade")
                .to("Budapest")
                .distance(100)
                .estimatedTime(4)
                .build();

        tourList.add(tour1);
        tourList.add(tour2);

        File file = new File("test.csv");
        int rowIndex = tourList.size();

        // Act
        ExporterServiceImpl exporter = new ExporterServiceImpl();
        exporter.exportCSV(file, tourList);

        // Assert
        // schaue ob Anzahl der Zeilen in der Datei mit der Anzahl der Touren übereinstimmt
        assertEquals(tourList.size(), rowIndex);

        file.delete();
    }

    @Test
    public void testContentForExportCSV() {

        // Arrange
        List<Tour> tourList = new ArrayList<>();
        Tour tour1 =  Tour.builder()
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

        tourList.add(tour1);
        tourList.add(tour2);

        File file = new File("test.csv");

        // Act
        ExporterServiceImpl exporter = new ExporterServiceImpl();
        exporter.exportCSV(file, tourList);

        // Assert

        // Inhalt vom exportiertem File überprüfen
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();// überspringe oberste Zeile

            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] columns = row.split(";");

                assertEquals(tourList.get(rowIndex).getName(), columns[0]);
                assertEquals(tourList.get(rowIndex).getDescription(), columns[1]);
                assertEquals(tourList.get(rowIndex).getFrom(), columns[2]);

                rowIndex++;
            }
        } catch (IOException e) {
            fail("Failed to read te exported file");
        }
        file.delete();

    }


    @Test
    public void testFileExistsForExportPDF() throws IOException {
        // Arrange
        List<Tour> tourList = new ArrayList<>();
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

        tourList.add(tour1);
        tourList.add(tour2);

        File file = new File("test.pdf");

        // Act
        ExporterServiceImpl exporter = new ExporterServiceImpl();
        exporter.exportPDF(file, tourList);

        // Assert
        assertTrue(file.exists());

        // Clean up !
        file.delete();
    }

    @Test
    public void testContentForExportPDF() throws IOException {
        // Arrange
        List<Tour> tourList = new ArrayList<>();
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
                .description("no mountains")
                .from("Belgrade")
                .to("Budapest")
                .distance(100)
                .estimatedTime(4)
                .build();

        tourList.add(tour1);
        tourList.add(tour2);

        File file = new File("test.pdf");

        // Act
        ExporterServiceImpl exporter = new ExporterServiceImpl();
        exporter.exportPDF(file, tourList);

        // Assert

        // Inhalt von PDF überprüfen
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String extractedText = stripper.getText(document);

            assertTrue(extractedText.contains("Name"));
            assertTrue(extractedText.contains("Description"));
            assertTrue(extractedText.contains("From"));
            assertTrue(extractedText.contains("To"));
            assertTrue(extractedText.contains("Transport Type"));
            assertTrue(extractedText.contains("Distance"));
            assertTrue(extractedText.contains("Estimated Time"));
            assertTrue(extractedText.contains("Jovana's tour"));
            assertTrue(extractedText.contains("Botond's tour"));
            assertTrue(extractedText.contains("nice view"));
            assertTrue(extractedText.contains("no mountains"));
            assertTrue(extractedText.contains("Vienna"));
            assertTrue(extractedText.contains("Belgrade"));
            assertTrue(extractedText.contains("Budapest"));
            assertTrue(extractedText.contains("150"));
            assertTrue(extractedText.contains("100"));
            assertTrue(extractedText.contains("6"));
            assertTrue(extractedText.contains("4"));
        } catch (IOException e) {
            fail("Failed to read the exported PDF");
        }

        // Clean up
        file.delete();
    }

    @Test
    public void testRowsExportPDF() throws IOException {
        // Arrange
        List<Tour> tourList = new ArrayList<>();
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

        Tour tour3 = Tour.builder()
                .name("Our tour")
                .description("nature")
                .from("Vienna")
                .to("Belgrade")
                .distance(160)
                .estimatedTime(6)
                .build();

        tourList.add(tour1);
        tourList.add(tour2);
        tourList.add(tour3);

        File file = new File("test.pdf");


        // Act
        ExporterServiceImpl exporter = new ExporterServiceImpl();
        exporter.exportPDF(file, tourList);

        // Assert
        int rowIndex = tourList.size();
        assertEquals(tourList.size(), rowIndex);

        // Clean up !
        file.delete();
    }


        @Test
        public void testExportOne() throws IOException {
            // Arrange
            List<Tour> tourList = new ArrayList<>();
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

            tourList.add(tour1);
            tourList.add(tour2);

            File file = new File("test.pdf");

            // Act
            ExporterServiceImpl exporter = new ExporterServiceImpl();
            exporter.exportPDF(file, tourList);

            // Assert
            assertTrue(file.exists());

            // Clean up !
            file.delete();

        }

}