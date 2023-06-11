package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.presentation.ApplicationController;
import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourLogViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourListViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourLogListViewModel;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Component
@Slf4j
public class NewTourLogController implements Initializable {

    private TourService tourService;
    @Autowired
    private SearchController searchController;

    private NewTourLogViewModel newTourLogViewModel;

    @Autowired
    public TourLogListViewModel tourLogListViewModel;

    @FXML
    private Text feedbackLogText;

//////////////////not choicebox
    @FXML
    private ChoiceBox<LocalDateTime> localDateTimeChoiceBox;
    ///////////////


    public TableView tableView = new TableView<>();
    @FXML
    private TextArea commentTextArea;
    @FXML
    private ChoiceBox<TourDifficulty> tourDifficultyChoiceBox;
    @FXML
    private TextField totalTourTimeField;
    @FXML
    private TextField ratingField;

    @FXML
    private VBox dataContainer;

    private Tour selectedTour;


    @Override
    public void initialize(URL location, ResourceBundle rb) {

        //datetime

        tableView.setItems(tourLogListViewModel.getTourLogItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setRowFactory(tv -> {
            TableRow<Tour> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    selectedTour = row.getItem();
                    System.out.println(selectedTour.getFrom());
                    System.out.println(selectedTour.getTo());

                }
            });
            return row;
        });

        TableColumn comment = new TableColumn("COMMENT");
        comment.setCellValueFactory(new PropertyValueFactory("comment"));
        TableColumn difficulty = new TableColumn("DIFFICULTY");
        difficulty.setCellValueFactory(new PropertyValueFactory("difficulty"));
        TableColumn totalTourTime = new TableColumn("TOTAL TOUR TIME");
        totalTourTime.setCellValueFactory(new PropertyValueFactory("totalTourTime"));
        TableColumn rating = new TableColumn("RATING");
        rating.setCellValueFactory(new PropertyValueFactory("rating"));

        tableView.getColumns().addAll(comment, difficulty, totalTourTime, rating);

        dataContainer.getChildren().add(tableView);
        tourLogListViewModel.initList();
    }



    public void setTour(Tour tour) {
        selectedTour = tour;
        newTourLogViewModel.setSelectedTour(tour);
    }
    public void submitButtonAction(ActionEvent event) {
        if(validateInput()) {
            newTourLogViewModel.setTourDifficulty(tourDifficultyChoiceBox.getValue());
            newTourLogViewModel.createTourLog();
            feedbackLogText.setText("TourLog has been added successfully");
        }

    }
    private boolean validateInput() {
        StringBuilder errorMessage = new StringBuilder();

        //datetime

        String comment = commentTextArea.getText();
        if(comment == null || comment.isEmpty()) {
            errorMessage.append("Comment field cannot be empty.\n");
        }

        TourDifficulty tourDifficulty = tourDifficultyChoiceBox.getValue();
        if(tourDifficulty == null) {
            errorMessage.append("Difficulty field cannot be empty.\n");
        }

        float totalTime = 0;
        try {
            totalTime = Float.parseFloat(totalTourTimeField.getText());
            if(totalTime == 0) {
                errorMessage.append("Time field cannot be zero.\n");
            }
        } catch (NumberFormatException e) {
            errorMessage.append("Time field must be a number.\n");
        }

        int rating = 0;
        try {
            rating = Integer.parseInt(ratingField.getText());
            if(rating == 0) {
                errorMessage.append("Rating field cannot be zero.\n");
            }
        }
        catch (NumberFormatException e) {
            errorMessage.append("Rating field must be a number between 1(very bad) and 5(very good).\n");
        }


        if(errorMessage.length() > 0) {
            // show error message in pop-up
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Some required fields are not filled.");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    public void setTourLog(Tour selectedTour) {
        setTour(selectedTour);
        commentTextArea.textProperty().bindBidirectional(newTourLogViewModel.commentProperty());
        tourDifficultyChoiceBox.getItems().setAll(TourDifficulty.values());
        newTourLogViewModel.tourDifficultyProperty().bindBidirectional(tourDifficultyChoiceBox.valueProperty());
        totalTourTimeField.textProperty().bindBidirectional(newTourLogViewModel.totalTourTimeProperty(), new NumberStringConverter());
        ratingField.textProperty().bindBidirectional(newTourLogViewModel.ratingProperty(), new NumberStringConverter());

    }
    /*
    //setter
    public void modifyTourLog(TourLog tourLog) {
        newTourLogViewModel.setTourLog(tourLog);
        commentTextArea.textProperty().bindBidirectional(newTourLogViewModel.commentProperty());
        tourDifficultyChoiceBox.getItems().setAll(TourDifficulty.values());
        newTourLogViewModel.tourDifficultyProperty().bindBidirectional(tourDifficultyChoiceBox.valueProperty());
        totalTourTimeField.textProperty().bindBidirectional(newTourLogViewModel.totalTourTimeProperty(), new NumberStringConverter());
        ratingField.textProperty().bindBidirectional(newTourLogViewModel.ratingProperty(), new NumberStringConverter());

    }
*/

}
