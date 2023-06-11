package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.TourLogRepository;
import at.fhtw.swen2.tutorial.presentation.ViewManager;
import at.fhtw.swen2.tutorial.presentation.viewmodel.LogEntry;
import at.fhtw.swen2.tutorial.presentation.viewmodel.LogEntry.Type;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourLogListViewModel;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class LogViewController implements Initializable {

    @FXML
    TableView<LogEntry> logTable;
    @FXML
    Button clearButton;
    @Autowired
    TourService tourService;

    TourLogEntity selectedTourlog;
    @FXML
    private TextField nameTextField;
    @Autowired
    TourLogListViewModel tourLogListViewModel;
    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();
    @FXML
    private TextArea commentTextArea;
    @FXML
    private ChoiceBox<TourDifficulty> tourDifficultyChoiceBox;
    @FXML
    private TextField totalTimeTextField;
    @FXML
    private TextField ratingTextField;
    @Autowired
    private TourLogRepository tourLogRepository;
    @Autowired
    private ViewManager viewManager;

    public LogViewController(TourLogRepository tourLogRepository) {
        this.tourLogRepository = tourLogRepository;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        initilizeClearButton();
        loadTourLogs();


        logTable.setRowFactory(tv -> {
            TableRow<LogEntry> row = new TableRow<>();

            // create a menu
            ContextMenu contextMenu = new ContextMenu();

            // create menuitems
            //MenuItem menuItem1 = new MenuItem("Modify");
            MenuItem menuItem2 = new MenuItem("Delete");
            //MenuItem menuItem3 = new MenuItem("Export");

            /*menuItem1.setOnAction((event) -> {
                LogEntry logEntry = row.getItem();
                try {
                    modify(logEntry);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });*/

            menuItem2.setOnAction((event) -> {
                LogEntry logEntry = row.getItem();
                delete(logEntry);
            });

            /*menuItem3.setOnAction((event) -> {
                selectedTourlog = row.getItem();
                try {
                    exportOne(selectedTourlog);
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error");
                    alert.setContentText("Could not export tour");
                    //logger.error("Export problem: Tour");
                    alert.showAndWait();
                }
            });*/


            // add menu items to menu
            //contextMenu.getItems().add(menuItem1);
            contextMenu.getItems().add(menuItem2);
            //contextMenu.getItems().add(menuItem3);

            row.setContextMenu(contextMenu);


            return row;
        });
    }

    private void delete(LogEntry selectedLogEntry) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            logTable.getItems().remove(selectedLogEntry);
            tourLogListViewModel.initList();
        }
    }

    /*private void modify(LogEntry selectedLogEntry) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen2/tutorial/presentation/view/ModifyTourLog.fxml"));
        Parent root = loader.load();

        ModifyTourLogController modifyTourLogController = loader.getController();
        modifyTourLogController.setLogEntry(selectedLogEntry);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(stage.getValue());
        dialog.setTitle("Modify Tour");

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(root);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.setDialogPane(dialogPane);

        dialog.showAndWait();

        tourLogListViewModel.initList();
    }*/






    //a helper method that binds the disableProperty of the clear button to the condition of whether the log table is empty. If the table is empty, the clear button will be disabled.
    void initilizeClearButton() {
        clearButton.disableProperty().bind(Bindings.isEmpty(logTable.getItems()));
    }

    // method that sets up the log table
    @SuppressWarnings("unchecked")
    void initializeTable() {
        ObservableList<LogEntry> entries = FXCollections.observableArrayList();
        logTable.setItems(entries); // TODO load real log data
        logTable.setPlaceholder(new Label(""));

        TableColumn<LogEntry, LocalDateTime> dateColumn = new TableColumn<>("Date and Time");
        dateColumn.prefWidthProperty().bind(logTable.widthProperty().multiply(0.25));
        dateColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDate()));
        dateColumn.setCellFactory(tableColumn -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                if (empty || item == null) this.setText("");
                else this.setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            }
        });
        TableColumn<LogEntry, String> messageColumn = new TableColumn<>("Comment");
        messageColumn.setSortable(false);
        messageColumn.prefWidthProperty().bind(logTable.widthProperty().multiply(0.5));
        messageColumn.setCellValueFactory(value -> value.getValue().getComment());
        messageColumn.setMaxWidth(Double.MAX_VALUE);

        TableColumn<LogEntry, TourDifficulty> difficultyColumn = new TableColumn<>("Difficulty");
        difficultyColumn.setSortable(false);
        difficultyColumn.prefWidthProperty().bind(logTable.widthProperty().multiply(0.1));
        difficultyColumn.setCellValueFactory(value -> value.getValue().getTourDifficulty());

        TableColumn<LogEntry, Float> totalTourTimeColumn = new TableColumn<>("Total Tour Time");
        totalTourTimeColumn.setSortable(false);
        totalTourTimeColumn.prefWidthProperty().bind(logTable.widthProperty().multiply(0.1));
        totalTourTimeColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getTotalTourTime()));

        TableColumn<LogEntry, Integer> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setSortable(false);
        ratingColumn.prefWidthProperty().bind(logTable.widthProperty().multiply(0.1));
        ratingColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getRating()));


/*
        TableColumn<LogEntry, Type> typeColumn = new TableColumn<>("Type");
        typeColumn.setSortable(false);
        typeColumn.prefWidthProperty().bind(logTable.widthProperty().multiply(0.1));
        typeColumn.setCellValueFactory(value -> value.getValue().getType()); // replaced with image*/

     /*   TableColumn<LogEntry, String> systemColumn = new TableColumn<>("System");
        systemColumn.setSortable(false);
        systemColumn.prefWidthProperty().bind(logTable.widthProperty().multiply(0.15));
        systemColumn.setCellValueFactory(value -> value.getValue().getSystemName());*/



        logTable.getColumns().addAll(dateColumn,messageColumn, difficultyColumn, totalTourTimeColumn, ratingColumn );
    }

    void loadTourLogs() {
        ObservableList<LogEntry> entries = FXCollections.observableArrayList();
        List<TourLogEntity> tourLogs = tourLogRepository.findAll();
        for (TourLogEntity tourLog : tourLogs) {
            LogEntry.Type type;
            if (tourLog.getType().equals("ERROR")) {
                type = LogEntry.Type.ERROR;
            } else if (tourLog.getType().equals("WARNING")) {
                type = LogEntry.Type.WARNING;
            } else {
                type = LogEntry.Type.INFO;
            }

            entries.add(new LogEntry(tourLog.getDate(), tourLog.getComment(),  tourLog.getDifficulty(), tourLog.getTotalTourTime(), tourLog.getRating()));
        }
        logTable.setItems(entries);
    }

    @FXML
    public void clearLogs(Event event) {
        log.debug("Clearing debug log data");
        logTable.getItems().clear();
    }

}