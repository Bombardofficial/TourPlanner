package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TourListViewModel {

    @Autowired
    TourService tourService;


    private List<Tour> masterData = new ArrayList<>();
    private ObservableList<Tour> tourListItems = FXCollections.observableArrayList();

    public ObservableList<Tour> getTourListItems() {
        return tourListItems;
    }

    public void addItem(Tour tour) {
        tourListItems.add(tour);
        masterData.add(tour);
    }

    public void clearItems(){ tourListItems.clear(); }

    public void initList(){
        tourService.getTourList().forEach(t -> {
            addItem(t);
        });
    }

    public void filterList(String searchText){ //filtering bei namen
        Task<List<Tour>> task = new Task<>() {
            @Override
            protected List<Tour> call() throws Exception {
                updateMessage("Loading data");
                return masterData
                        .stream()
                        .filter(value -> value.getName().toLowerCase().contains(searchText.toLowerCase())
                                || value.getDescription().toLowerCase().contains(searchText.toLowerCase())
                                || value.getFrom().toLowerCase().contains(searchText.toLowerCase())
                                || value.getTo().toLowerCase().contains(searchText.toLowerCase())
                                || String.valueOf(value.getTransportType()).toLowerCase().contains(searchText.toLowerCase())
                                || String.valueOf(value.getDistance()).toLowerCase().contains(searchText.toLowerCase())
                                || String.valueOf(value.getEstimatedTime()).toLowerCase().contains(searchText.toLowerCase())
                        ).collect(Collectors.toList());
            }
        };

        task.setOnSucceeded(event -> {
            tourListItems.setAll(task.getValue());
        });

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }


}
