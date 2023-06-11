package at.fhtw.swen2.tutorial.presentation.viewmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class SearchViewModelTest {
/*
    @Test
    public void testSearch() {
        // Arrange
        String searchText = "your search text";
        boolean searchTourLogs = true;

        // Create the mock dependencies
        TourLogListViewModel tourLogListViewModel = Mockito.mock(TourLogListViewModel.class);
        TourListViewModel tourListViewModel = Mockito.mock(TourListViewModel.class);

        // Create an instance of the class under test and set the mock dependencies
        SearchViewModel searchViewModel = new SearchViewModel();
        searchViewModel.setTourLogListViewModel(tourLogListViewModel);
        searchViewModel.setTourListViewModel(tourListViewModel);

        // Act
        searchViewModel.search();

        // Assert
        if (searchTourLogs) {
            verify(tourLogListViewModel).filterList(searchText);
            verify(tourListViewModel, Mockito.never()).filterList(searchText);
        } else {
            verify(tourListViewModel).filterList(searchText);
            verify(tourLogListViewModel, Mockito.never()).filterList(searchText);
        }
    }



   @Mock
   private TourLogListViewModel tourLogListViewModel;

    @Mock
    private TourListViewModel tourListViewModel;

    @Test
    public void testSearch() {


        // Arrange
        String searchText = "your search text";
        boolean searchTourLogs = true;
        SearchViewModel searchViewModel = new SearchViewModel(tourLogListViewModel, tourListViewModel);

        // Act
        searchViewModel.search();

        // Assert
        if (searchTourLogs) {
            verify(tourLogListViewModel).filterList(searchText);
            verify(tourListViewModel, Mockito.never()).filterList(searchText);
        } else {
            verify(tourListViewModel).filterList(searchText);
            verify(tourLogListViewModel, Mockito.never()).filterList(searchText);
        }
    }
*/
}