package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourListViewModel;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TourListController implements Initializable{

    @Autowired
    public TourListViewModel tourListViewModel;

    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;
    private Tour selectedTour;

    @Autowired
    MapQuestService mapQuestService;

    @FXML
    ImageView map = new ImageView();
    @FXML
    private ImageView imageView;
    @Override
    public void initialize(URL location, ResourceBundle rb){
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
                }
            });
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





}
