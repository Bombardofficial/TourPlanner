package at.fhtw.swen2.tutorial.presentation;

import at.fhtw.swen2.tutorial.presentation.view.ApplicationShutdownEvent;
import at.fhtw.swen2.tutorial.presentation.view.AboutDialogController;
import at.fhtw.swen2.tutorial.presentation.view.ModifyTourController;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourListViewModel;
import at.fhtw.swen2.tutorial.service.ExporterService;
import at.fhtw.swen2.tutorial.service.CsvImporterService;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class ApplicationController implements Initializable, StageAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    ApplicationEventPublisher publisher;

    @FXML BorderPane layout;

    // Menu, at some point break out
    @FXML MenuItem miPreferences;
    @FXML MenuItem miQuit;
    @FXML MenuItem miAbout;

    @FXML
    private AnchorPane dataContainer2;

    private boolean showTour = true; // Initially show Tour.fxml
    
    // Toolbar, at some point break out
    @FXML Label tbMonitorStatus;
    Circle monitorStatusIcon = new Circle(8);

    // Map
    @Autowired
    MapQuestService mapQuestService;

    @FXML
    ImageView map;


    @Autowired
    public TourListViewModel tourListViewModel;

    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;
    private Tour selectedTour;

    @Autowired
    TourService tourService;
    @Autowired
    ExporterService exporter;
    @Autowired
    CsvImporterService csvImporterService;
    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();
    @Autowired
    private ViewManager viewManager;

    @Autowired
    private ModifyTourController modifyTourController;


    public ApplicationController(ApplicationEventPublisher publisher) {
        log.debug("Initializing application controller");
        this.publisher = publisher;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        stage.addListener((obv, o, n) -> n.setTitle(rb.getString("app.title")));
        Image mapImage = mapQuestService.getMap("Paris","Vienna");
        map.setImage(mapImage);


        tableView.setItems(tourListViewModel.getTourListItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setRowFactory( tv -> {
            TableRow<Tour> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    selectedTour = row.getItem();
                    System.out.println(selectedTour.getFrom());
                    System.out.println(selectedTour.getTo());

                    Image nextMap = mapQuestService.getMap(selectedTour.getFrom(), selectedTour.getTo());
                    map.setImage(nextMap);
                    System.out.println("nextMap: " + nextMap);
                }
            });

            // create a menu
            ContextMenu contextMenu = new ContextMenu();

            // create menuitems
            MenuItem menuItem1 = new MenuItem("Modify");
            MenuItem menuItem2 = new MenuItem("Delete");
            MenuItem menuItem3 = new MenuItem("Export");
            menuItem1.setOnAction((event) -> {
                selectedTour = row.getItem();
                try {
                    modify(selectedTour);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            menuItem2.setOnAction((event) -> {
                selectedTour = row.getItem();
                delete(selectedTour);
            });

            menuItem3.setOnAction((event) -> {
                selectedTour = row.getItem();
                try {
                    exportOne(selectedTour);
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error");
                    alert.setContentText("Could not export tour");
                    logger.error("Export problem: Tour");
                    alert.showAndWait();
                }
            });

            // add menu items to menu
            contextMenu.getItems().add(menuItem1);
            contextMenu.getItems().add(menuItem2);
            contextMenu.getItems().add(menuItem3);

            row.setContextMenu(contextMenu);



            return row;
        });

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn description = new TableColumn("DESCRIPTION");
        description.setCellValueFactory(new PropertyValueFactory("description"));
        TableColumn from = new TableColumn("FROM");
        from.setCellValueFactory(new PropertyValueFactory("from"));
        TableColumn to = new TableColumn("TO");
        to.setCellValueFactory(new PropertyValueFactory("to"));
        TableColumn transportType = new TableColumn("TRANSPORT TYPE");
        transportType.setCellValueFactory(new PropertyValueFactory("transportType"));
        TableColumn distance = new TableColumn("DISTANCE (km)");
        distance.setCellValueFactory(new PropertyValueFactory("distance"));
        TableColumn estimatedTime = new TableColumn("ESTIMATED TIME (h)");
        estimatedTime.setCellValueFactory(new PropertyValueFactory("estimatedTime"));
        tableView.getColumns().addAll(id, name, description, from, to, transportType, distance, estimatedTime);

        dataContainer.getChildren().add(tableView);
        tourListViewModel.initList();

    }
    private void exportOne(Tour selectedTour) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Tour");
        fileChooser.setInitialFileName(selectedTour.getName() + ".pdf");
        File file = fileChooser.showSaveDialog(stage.getValue());
        if (file != null) {
            exporter.exportOne(file, selectedTour);
        }
    }


    private void delete(Tour selectedTour) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selectedTour.getName() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            tourService.deleteTour(selectedTour);
            tourListViewModel.initList();
        }

    }

    private void modify(Tour selectedTour) throws  IOException {

        Dialog<String> dialog = viewManager.load("/at/fhtw/swen2/tutorial/presentation/view/ModifyTour", stage.getValue());


        dialog.initOwner(stage.getValue());
        dialog.setTitle("Modify Tour");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        modifyTourController.setTour(selectedTour);

        dialog.showAndWait();



        tourListViewModel.initList();
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
    private void onExport() throws IOException {
        // Create a new FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Tour Data");

        // Set the extension filters
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilterPDF);

        // Show the dialog and wait for user input
        File file = fileChooser.showSaveDialog(null);

        // If a file was selected, export the data to the file
        if (file != null) {
            List<Tour> tourList = tourService.getTourList();
            System.out.println(tourList);

            if(fileChooser.getSelectedExtensionFilter().equals(extFilterPDF))
            {
                exporter.exportPDF(file, tourList);
            }
            else if(fileChooser.getSelectedExtensionFilter().equals(extFilter))
            {
                exporter.exportCSV(file, tourList);
            }

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

            try {
                List<Tour> tours = csvImporterService.importFile(file);
                for (Tour tour : tours) {
                    tourService.addNew(tour);
                }
                tourListViewModel.clearItems();
                tourListViewModel.initList();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void addNewTourLog(ActionEvent event) {
        try {
            if (showTour) {
                dataContainer.getChildren().remove(tableView);
                // Load and display TourLog.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/TourLog.fxml"));
                Parent tourLogRoot = loader.load();
                dataContainer.getChildren().setAll(tourLogRoot);
                showTour = false;
            } else {
                // Load and display Tour.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Tour.fxml"));
                Parent tourRoot = loader.load();
                dataContainer.getChildren().setAll(tourRoot);
                showTour = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void setStage(Stage stage) {
        this.stage.setValue(stage);
    }
}
