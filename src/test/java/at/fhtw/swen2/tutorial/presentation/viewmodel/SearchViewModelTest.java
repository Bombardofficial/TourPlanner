package at.fhtw.swen2.tutorial.presentation.viewmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchViewModelTest {
    @Mock
    private TourLogListViewModel tourLogListViewModel;
    @Mock
    private TourListViewModel tourListViewModel;
    private SearchViewModel searchViewModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        searchViewModel = new SearchViewModel(tourLogListViewModel, tourListViewModel);
    }

    @Test
    public void testGetSearchString() {
        // Arrange
        searchViewModel.setSearchString("Vienna");

        // Act
        String searchString = searchViewModel.getSearchString();

        // Assert
        assertEquals("Vienna", searchString);
    }

    @Test
    public void testSetSearchString() {
        // Act
        searchViewModel.setSearchString("Tour 1");

        // Assert
        assertEquals("Tour 1", searchViewModel.getSearchString());
    }

    @Test
    public void testIsSearchForTourLogs() {
        // Arrange
        searchViewModel.setSearchForTourLogs(true);

        // Act
        boolean searchForTourLogs = searchViewModel.isSearchForTourLogs();

        // Assert
        assertEquals(true, searchForTourLogs);
    }

    @Test
    public void testSetSearchForTourLogs() {
        // Act
        searchViewModel.setSearchForTourLogs(false);

        // Assert
        assertEquals(false, searchViewModel.isSearchForTourLogs());
    }

    @Test
    public void testSearch_WhenSearchForTourLogsTrue() {
        // Arrange
        searchViewModel.setSearchString("Jovana's Tour");
        searchViewModel.setSearchForTourLogs(true);

        // Act
        searchViewModel.search();

        // Assert
        verify(tourLogListViewModel).filterList("Jovana's Tour");
    }

    @Test
    public void testSearch_notFound() {
        // Arrange
        searchViewModel.setSearchString(null);
        searchViewModel.setSearchForTourLogs(false);

        // Act
        searchViewModel.search();

        // Assert
        verify(tourListViewModel).filterList(null);
    }
}