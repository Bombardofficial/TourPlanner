package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.model.Tour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import at.fhtw.swen2.tutorial.service.ExporterService;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import at.fhtw.swen2.tutorial.persistence.repositories.TourLogRepository;
import javax.swing.text.StyleConstants;

@Service
@Transactional
public class ExporterServiceImpl implements ExporterService {

    private static final String CSV_SEPARATOR = ";";

    @Autowired
    public MapQuestService mapQuestService;
    @Autowired
    private TourLogRepository tourLogRepository;


    public void exportCSV(File file, List<Tour> tourList) {
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

    @Override
    public void exportPDF(File file, List<Tour> tourList) throws IOException {
        var writer = new PdfWriter(file);
        var pdf = new PdfDocument(writer);
        var document = new Document(pdf);

        Paragraph header = new Paragraph("Summary report:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(18)
                .setBold()
                .setFontColor(ColorConstants.RED);
        document.add(header);

        Table table = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();
        table.addHeaderCell("Name");
        table.addHeaderCell("Description");
        table.addHeaderCell("From");
        table.addHeaderCell("To");
        table.addHeaderCell("Transport Type");
        table.addHeaderCell("Distance");
        table.addHeaderCell("Estimated Time");
        table.setFontSize(10);
        table.setFontColor(ColorConstants.BLACK);

        for (Tour tour : tourList) {
            table.addCell(tour.getName());
            if(tour.getDescription() == null)
                table.addCell("-");
            else
                table.addCell(tour.getDescription());
            table.addCell(tour.getFrom());
            table.addCell(tour.getTo());
            table.addCell(String.valueOf(tour.getTransportType()));
            table.addCell(Float.toString(tour.getDistance()));
            table.addCell(Float.toString(tour.getEstimatedTime()));

        }
        document.add(table);




        document.close();
    }

    @Override
    public void exportOne(File file, Tour tour) throws IOException {
        var writer = new PdfWriter(file);
        var pdf = new PdfDocument(writer);
        var document = new Document(pdf);

        Paragraph header = new Paragraph(tour.getName()+"'s report:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(18)
                .setBold()
                .setFontColor(ColorConstants.RED);
        document.add(header);

        Table table = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();
        table.addHeaderCell("Name");
        table.addHeaderCell("Description");
        table.addHeaderCell("From");
        table.addHeaderCell("To");
        table.addHeaderCell("Transport Type");
        table.addHeaderCell("Distance");
        table.addHeaderCell("Estimated Time");
        table.setFontSize(10);
        table.setFontColor(ColorConstants.BLACK);


        table.addCell(tour.getName());
        if(tour.getDescription() == null)
            table.addCell("-");
        else
            table.addCell(tour.getDescription());
        table.addCell(tour.getFrom());
        table.addCell(tour.getTo());
        table.addCell(String.valueOf(tour.getTransportType()));
        table.addCell(Float.toString(tour.getDistance()));
        table.addCell(Float.toString(tour.getEstimatedTime()));

        document.add(table);

        List<TourLogEntity> tourLogs = tourLogRepository.findByTourId(tour.getId());
        if (!tourLogs.isEmpty()) {
            document.add(new Paragraph("Tour Logs:").setFontSize(12).setBold());

            Table logTable = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
            logTable.addHeaderCell("Date");
            logTable.addHeaderCell("Comment");
            logTable.addHeaderCell("Difficulty");
            logTable.addHeaderCell("Total Tour Time");
            logTable.addHeaderCell("Rating");
            logTable.setFontSize(10);
            logTable.setFontColor(ColorConstants.BLACK);

            for (TourLogEntity tourLogEntity : tourLogs) {
              //  logTable.addCell(tourLogEntity.getDate().toString());
                logTable.addCell(tourLogEntity.getComment());
                logTable.addCell(String.valueOf(tourLogEntity.getDifficulty()));
                logTable.addCell(Float.toString(tourLogEntity.getTotalTourTime()));
                logTable.addCell(Integer.toString(tourLogEntity.getRating()));
            }

            document.add(logTable);
        }

        Paragraph imageHeader = new Paragraph("Map")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(12)
                .setBold();
        document.add(imageHeader);
        ImageData data = ImageDataFactory.create(mapQuestService.getMapByteArray(tour.getFrom(), tour.getTo()));

        Image image = new Image(data);

        image.scale(0.2f, 0.2f);
        document.add(image);


        document.close();
    }
}