package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourViewModel;
import at.fhtw.swen2.tutorial.service.TourService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
//@Scope("prototype")
@Slf4j
public class NewTourController implements Initializable {

    @Autowired
    private TourService tourService;
    @Autowired
    private SearchController searchController;
    @Autowired
    private NewTourViewModel newTourViewModel;

    @FXML
    private Text feedbackText;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
    @FXML
    private ChoiceBox<TransportType> transportTypeChoiceBox;
    @FXML
    private TextField distanceTextField;
    @FXML
    private TextField estimatedTimeTextField;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        nameTextField.textProperty().bindBidirectional(newTourViewModel.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(newTourViewModel.descriptionProperty());
        fromTextField.textProperty().bindBidirectional(newTourViewModel.fromProperty());
        toTextField.textProperty().bindBidirectional(newTourViewModel.toProperty());
        transportTypeChoiceBox.setItems(FXCollections.observableArrayList(TransportType.values()));
        newTourViewModel.transportTypeProperty().bind(transportTypeChoiceBox.valueProperty());
        distanceTextField.textProperty().bindBidirectional(newTourViewModel.distanceProperty(), new NumberStringConverter());
        estimatedTimeTextField.textProperty().bindBidirectional(newTourViewModel.estimatedTimeProperty(), new NumberStringConverter());

    }

    public void submitButtonAction(ActionEvent event) {
        if (nameTextField.getText().isEmpty()) {
            feedbackText.setText("nothing entered!");
            return;
        }

        newTourViewModel.addNewTour();
    }
}
