package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty.easy;
import static at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty.moderate;
import static java.util.Collections.addAll;
import static org.junit.jupiter.api.Assertions.*;

class TourLogListViewModelTest {

    TourLogListViewModel tourLogListViewModel = new TourLogListViewModel();


    @Test
    public void testAddItem() {
        // Arrange
        TourLog log1 = TourLog.builder()
                .comment("cha cha cha")
               // .LocalDateTime(2007-12-03T15:30:30.)
                .tourDifficulty(moderate)
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
                // .LocalDateTime(2007-12-03T15:30:30.)
                .tourDifficulty(moderate)
                .totalTourTime(2)
                .rating(4)
                .build();

        TourLog log2 = TourLog.builder()
                .comment("schön wars")
                // .LocalDateTime(2007-12-03T15:30:30.)
                .tourDifficulty(easy)
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



/*
    @Test
    public void testGetTourLogItems() {

        TourLog log1 = TourLog.builder()
                .comment("cha cha cha")
                // .LocalDateTime(2007-12-03T15:30:30.)
                .tourDifficulty(moderate)
                .totalTourTime(2)
                .rating(4)
                .build();

        TourLog log2 = TourLog.builder()
                .comment("schön wars")
                // .LocalDateTime(2007-12-03T15:30:30.)
                .tourDifficulty(easy)
                .totalTourTime(2)
                .rating(4)
                .build();


        ObservableList<TourLog> tourLogList;
        addAll(Arrays.asList(log1, log2));


        //Act
        ObservableList<TourLog> result = tourLogListViewModel.getTourLogItems();

        // Assert das Liste gleiche Element wie original list hat
        Assertions.assertEquals(tourLogList, result);
    }*/
}