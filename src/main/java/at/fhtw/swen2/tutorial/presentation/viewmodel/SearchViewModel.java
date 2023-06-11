package at.fhtw.swen2.tutorial.presentation.viewmodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SearchViewModel {

    public SearchViewModel(TourLogListViewModel tourLogListViewModel, TourListViewModel tourListViewModel) {
        this.tourLogListViewModel = tourLogListViewModel;
        this.tourListViewModel = tourListViewModel;
    }

    @Autowired
    private TourListViewModel tourListViewModel;
    @Autowired
    private TourLogListViewModel tourLogListViewModel;

    private SimpleStringProperty searchString = new SimpleStringProperty();
    private SimpleBooleanProperty searchForTourLogs = new SimpleBooleanProperty(false);


    public String getSearchString() {
        return searchString.get();
    }
    public SimpleStringProperty searchStringProperty() {
        return searchString;
    }
    public void setSearchString(String searchString) {
        this.searchString.set(searchString);
    }

    public boolean isSearchForTourLogs() {
        return searchForTourLogs.get();
    }
    public SimpleBooleanProperty searchForTourLogsProperty() {
        return searchForTourLogs;
    }
    public void setSearchForTourLogs(boolean searchForTourLogs) {
        this.searchForTourLogs.set(searchForTourLogs);
    }

  /*  public void search() {
        tourListViewModel.filterList(getSearchString());
    }*/
  public void search() {
      String searchText = getSearchString();
      boolean searchTourLogs = isSearchForTourLogs();

      if (searchTourLogs) {
          tourLogListViewModel.filterList(searchText);
      } else {
          tourListViewModel.filterList(searchText);
      }
  }



}
