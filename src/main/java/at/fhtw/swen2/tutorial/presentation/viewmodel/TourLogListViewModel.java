package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TourLogListViewModel {

    @Autowired
    private TourService tourService;

    private List<TourLog> masterData = new ArrayList<>();
    private ObservableList<TourLog> tourLogItems = FXCollections.observableArrayList();

    public ObservableList<TourLog> getTourLogItems() {
        return tourLogItems;
    }

    public void addItem(TourLog tourLog) {
        tourLogItems.add(tourLog);
        masterData.add(tourLog);
    }

    public void clearItems() {
        tourLogItems.clear();
    }

    public void initList(Tour tour) {
        tourLogItems.setAll(tour.getTourLogs());
    }

    public void filterList(String searchText) {
        Task<List<TourLog>> task = new Task<>() {
            @Override
            protected List<TourLog> call() throws Exception {
                updateMessage("Loading data");
                return masterData.stream()
                        .filter(value -> value.getComment().toLowerCase().contains(searchText.toLowerCase())
                                || String.valueOf(value.getTourDifficulty()).toLowerCase().contains(searchText.toLowerCase())
                                || String.valueOf(value.getTotalTourTime()).toLowerCase().contains(searchText.toLowerCase())
                                || String.valueOf(value.getRating()).toLowerCase().contains(searchText.toLowerCase())
                        ).collect(Collectors.toList());
            }
        };

        task.setOnSucceeded(event -> tourLogItems.setAll(task.getValue()));

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }


}
