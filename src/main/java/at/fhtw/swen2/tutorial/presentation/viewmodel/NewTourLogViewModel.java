package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.beans.property.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewTourLogViewModel {

    private SimpleLongProperty id = new SimpleLongProperty();

    //datetime

    private SimpleStringProperty comment = new SimpleStringProperty();
    private SimpleObjectProperty<TourDifficulty> tourDifficulty = new SimpleObjectProperty<>();
    private SimpleFloatProperty totalTourTime = new SimpleFloatProperty();
    private SimpleIntegerProperty rating = new SimpleIntegerProperty();

 //   private SimpleLongProperty tourId = new SimpleLongProperty();
    @Autowired
    private TourService tourService;
    @Autowired
    private TourLogListViewModel tourListLogViewModel;

    private TourLog tourLog;
    private Tour tour;
    private Tour selectedTour;

    public void setTourLog(TourLog tourLog) {
        this.tourLog = tourLog;
       // this.tour = tour;
        this.id = new SimpleLongProperty(tourLog.getId());
        this.comment = new SimpleStringProperty(tourLog.getComment());
        this.tourDifficulty = new SimpleObjectProperty<>(tourLog.getTourDifficulty());
        this.totalTourTime = new SimpleFloatProperty(tourLog.getTotalTourTime());
        this.rating = new SimpleIntegerProperty(tourLog.getRating());
    }
    public void setTourLog(TourLog tourLog, Tour selectedTour) {
        this.selectedTour = selectedTour;
        this.tourLog = tourLog;
        this.id = new SimpleLongProperty(tourLog.getId());
        this.comment = new SimpleStringProperty(tourLog.getComment());
        //datetime

        this.tourDifficulty = new SimpleObjectProperty<>(tourLog.getTourDifficulty());
        this.totalTourTime = new SimpleFloatProperty(tourLog.getTotalTourTime());
        this.rating = new SimpleIntegerProperty(tourLog.getRating());
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    //datetime

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public TourDifficulty getTourDifficulty() {
        return tourDifficulty.get();
    }

    public void setTourDifficulty(TourDifficulty tourDifficulty) {
        this.tourDifficulty.set(tourDifficulty);
    }

    public ObjectProperty<TourDifficulty> tourDifficultyProperty() {
        return tourDifficulty;
    }

    public float getTotalTourTime() {
        return totalTourTime.get();
    }

    public void setTotalTourTime(float totalTourTime) {
        this.totalTourTime.set(totalTourTime);
    }

    public FloatProperty totalTourTimeProperty() {
        return totalTourTime;
    }

    public int getRating() {
        return rating.get();
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public Tour getTour() {
        return tour;
    }
    public void setTour(Tour tour) {
        this.tour =tour;
    }
/*
    public long getTourId() {
        return tourId.get();
    }

    public long setTourId(int tourId) {this.tourId.set(tourId);
        return 0;
    }
    public LongProperty tourIdProperty()  {return tourId; }
*/

    public void createTourLog() {
        if (selectedTour != null) {
            TourLog tourLog = TourLog.builder()

                    //datetime

                    .comment(getComment())
                    .tourDifficulty(getTourDifficulty())
                    .totalTourTime(getTotalTourTime())
                    .rating(getRating())
                  //  .TourId(selectedTour.getId())

                    .build();
            tourLog = tourService.addTourLog(tourLog);
          //  selectedTour.getTourLogs().add(tourLog);
            tourListLogViewModel.addItem(tourLog);
        }
    }
}


