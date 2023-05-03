package at.fhtw.swen2.tutorial.presentation;

import at.fhtw.swen2.tutorial.presentation.view.ApplicationShutdownEvent;
import at.fhtw.swen2.tutorial.presentation.view.AboutDialogController;
import at.fhtw.swen2.tutorial.service.CsvImporterService;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.impl.CsvExporterServiceImpl;
import at.fhtw.swen2.tutorial.service.impl.CsvImporterServiceImpl;
import at.fhtw.swen2.tutorial.service.impl.TourServiceImpl;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class ApplicationController implements Initializable, StageAware {

    ApplicationEventPublisher publisher;

    @FXML BorderPane layout;

    // Menu, at some point break out
    @FXML MenuItem miPreferences;
    @FXML MenuItem miQuit;
    @FXML MenuItem miAbout;
    
    // Toolbar, at some point break out
    @FXML Label tbMonitorStatus;
    Circle monitorStatusIcon = new Circle(8);

    // Map
    @Autowired
    MapQuestService mapQuestService;

    @FXML
    ImageView map;



    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();

    public ApplicationController(ApplicationEventPublisher publisher) {
        log.debug("Initializing application controller");
        this.publisher = publisher;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        stage.addListener((obv, o, n) -> n.setTitle(rb.getString("app.title")));
        Image mapImage = mapQuestService.getMap("Mezobereny","Vienna");
        map.setImage(mapImage);

        /*ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(map);

// Center the image in the scroll pane
        scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
            double width = newValue.getWidth();
            double height = newValue.getHeight();
            double contentWidth = map.prefWidth(0);
            double contentHeight = map.prefHeight(0);

            if (contentWidth < width) {
                map.setLayoutX((width - contentWidth) / 2);
            } else {
                map.setLayoutX(0);
            }

            if (contentHeight < height) {
                map.setLayoutY((height - contentHeight) / 2);
            } else {
                map.setLayoutY(0);
            }
        });

        // Add zooming functionality
        map.setOnScroll(event -> {
            double delta = event.getDeltaY();
            double scale = map.getScaleX();
            double zoomFactor = 1.5;

            if (delta < 0) {
                scale /= zoomFactor;
            } else {
                scale *= zoomFactor;
            }

            map.setScaleX(scale);
            map.setScaleY(scale);

            // Re-center the image after zooming
            double width = scrollPane.getViewportBounds().getWidth();
            double height = scrollPane.getViewportBounds().getHeight();
            double contentWidth = map.prefWidth(0);
            double contentHeight = map.prefHeight(0);

            if (contentWidth < width) {
                map.setLayoutX((width - contentWidth) / 2);
            } else {
                map.setLayoutX(0);
            }

            if (contentHeight < height) {
                map.setLayoutY((height - contentHeight) / 2);
            } else {
                map.setLayoutY(0);
            }
        });

        Tab tab = new Tab("%tab.secondtab.title");
        tab.setContent(scrollPane);*/
    }

    @FXML
    public void onFileClose(ActionEvent event) {
        publisher.publishEvent(new ApplicationShutdownEvent(event.getSource()));
    }

    @FXML 
    public void onHelpAbout(ActionEvent event) {
        new AboutDialogController().show();
    }
    @FXML
    private void onExport() {
        // Create a new FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Tour Data");

        // Set the extension filters
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the dialog and wait for user input
        File file = fileChooser.showSaveDialog(null);

        // If a file was selected, export the data to the file
        if (file != null) {
            TourService tourService = new TourServiceImpl();
            List<Tour> tourList = tourService.getTourList();
            System.out.println(tourList);
            CsvExporterServiceImpl exporter = new CsvExporterServiceImpl();
            exporter.export(file, tourList);
        }
    }

    @FXML
    private void onImport() {
        // Create a new FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Tour Data");

        // Set the extension filters
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the dialog and wait for user input
        File file = fileChooser.showOpenDialog(null);

        // If a file was selected, import the data from the file
        if (file != null) {
            TourService tourService = new TourServiceImpl();
            CsvImporterService csvImporterService = new CsvImporterServiceImpl();
            try {
                List<Tour> tours = csvImporterService.importFile(file);
                for (Tour tour : tours) {
                    tourService.addNew(tour);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void setStage(Stage stage) {
        this.stage.setValue(stage);
    }
}
