package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.PersonListViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourListViewModel;
import at.fhtw.swen2.tutorial.service.PersonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TourListController implements Initializable{

    @Autowired
    public TourListViewModel tourListViewModel;

    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;

    @Override
    public void initialize(URL location, ResourceBundle rb){
        tableView.setItems(tourListViewModel.getTourListItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
        TableColumn transportType = new TableColumn("TRANSPORTTYPE");
        transportType.setCellValueFactory(new PropertyValueFactory("transportType"));
        TableColumn distance = new TableColumn("DISTANCE");
        distance.setCellValueFactory(new PropertyValueFactory("distance"));
        TableColumn estimatedTime = new TableColumn("ESTIMATEDTIME");
        estimatedTime.setCellValueFactory(new PropertyValueFactory("estimatedTime"));
        tableView.getColumns().addAll(id, name, description, from, to, transportType, distance, estimatedTime);

        dataContainer.getChildren().add(tableView);
        tourListViewModel.initList();
    }

}
