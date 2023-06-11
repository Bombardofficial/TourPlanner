package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty.*;
import static org.junit.jupiter.api.Assertions.*;

class TourLogListViewModelTest {

    TourLogListViewModel tourLogListViewModel = new TourLogListViewModel();


    @Test
    public void testAddItem() {
        // Arrange
        TourLog log1 = TourLog.builder()
                .comment("cha cha cha")
                .tourDifficulty(MODERATE)
                .totalTourTime(2)
                .rating(4)
                .build();

        ObservableList<TourLog> tourListItems = tourLogListViewModel.getTourLogItems();

        // Act
        tourLogListViewModel.addItem(log1);

        // Assert
        assertEquals(1, tourListItems.size());
        assertEquals(log1, tourListItems.get(0));
    }

    @Test
    void testClearItems() {

        // Arrange
        TourLog log1 = TourLog.builder()
                .comment("cha cha cha")
                .tourDifficulty(MODERATE)
                .totalTourTime(2)
                .rating(4)
                .build();

        TourLog log2 = TourLog.builder()
                .comment("schön wars")
                .tourDifficulty(EASY)
                .totalTourTime(2)
                .rating(4)
                .build();

        ObservableList<TourLog> tourLogListItems = tourLogListViewModel.getTourLogItems();
        tourLogListItems.add(log1);
        tourLogListItems.add(log2);

        // Act
        tourLogListViewModel.clearItems();

        // Assert
        assertEquals(0, tourLogListItems.size(), "Tour list should be empty after clearing");
    }

}