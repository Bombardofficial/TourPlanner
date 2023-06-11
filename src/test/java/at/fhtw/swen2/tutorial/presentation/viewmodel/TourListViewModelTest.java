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
    /*
    private TourService tourService;
    private TourListViewModel tourListViewModel;


    @BeforeEach
    void setup() {
        // Create a mock TourService
        tourService = mock(TourService.class);

        // Create the TourListViewModel instance with the mock TourService
        tourListViewModel = new TourListViewModel();
    }

    @Test
    public void creatingTourList() {
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

        when(tourService.getTourList()).thenReturn(List.of(tour1, tour2));

        // Act
        tourListViewModel.initList();

        // Assert
        assertEquals(2, tourListViewModel.getTourListItems().size(), "List should contain two items");
        assertEquals(tour1, tourListViewModel.getTourListItems().get(0), "List tour should match the expected tour");


    }*/
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




/*
    @Test
    public void testInitList() {
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
        tourListViewModel.initList();

        // Assert
        assertEquals(2, tourListViewModel.getTourListItems().size(), "List should contain two items");
        assertEquals(tour1, tourListViewModel.getTourListItems().get(0), "List tour should match the expected tour");


    }*/

}