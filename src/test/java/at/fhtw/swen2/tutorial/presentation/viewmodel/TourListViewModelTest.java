package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TourListViewModelTest {
    private TourListViewModel tourListViewModel;
    private ObservableList<Tour> tourListItems;

    @BeforeEach
    void setup() {
        tourListViewModel = new TourListViewModel();
        tourListItems = tourListViewModel.getTourListItems();
    }

    @Test
    public void testAddItem() {
        // Arrange
        Tour tour = Tour.builder()
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .build();

        ObservableList<Tour> tourListItems = tourListViewModel.getTourListItems();

        // Act
        tourListViewModel.addItem(tour);

        // Assert
        Assertions.assertTrue(tourListItems.contains(tour));
    }

    @Test
    void testClearItems() {

        // Arrange
        Tour tour1 = Tour.builder()
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .build();

        Tour tour2 = Tour.builder()
                .name("Botond's tour")
                .description("no mountains in Hungary")
                .from("Belgrade")
                .to("Budapest")
                .distance(100)
                .estimatedTime(4)
                .build();

        tourListItems.add(tour1);
        tourListItems.add(tour2);

        // Act
        tourListViewModel.clearItems();

        // Assert
        assertEquals(0, tourListItems.size(), "Tour list should be empty after clearing");
    }

}