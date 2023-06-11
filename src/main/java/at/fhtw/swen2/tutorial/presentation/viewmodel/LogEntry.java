package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import javafx.beans.property.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LogEntry {


    public LogEntry() {

    }

    public static enum Type {
        ERROR,
        WARNING,
        INFO
    }

    Property<Type> type = new SimpleObjectProperty<>(this, "");
    Property<LocalDateTime> date = new SimpleObjectProperty<>(this, "date");
    // Property<String> systemName = new SimpleStringProperty(this, "systemName");
    Property<String> comment = new SimpleStringProperty(this, "comment");

    Property<TourDifficulty> tourDifficulty = new SimpleObjectProperty<>(this, "tourDifficulty");
    SimpleFloatProperty totalTourTime = new SimpleFloatProperty(this, "totalTourTime");
    SimpleIntegerProperty rating = new SimpleIntegerProperty(this, "rating");

    public LogEntry(LocalDateTime date, String comment, TourDifficulty tourDifficulty, Float totalTourTime, Integer rating) {
        // this.type.setValue(type);
        this.date.setValue(date);
        this.comment.setValue(comment);
        this.tourDifficulty.setValue(tourDifficulty);
        this.totalTourTime.setValue(totalTourTime);
        this.rating.setValue(rating);
    }

    public Property<Type> typeProperty() {
        return type;
    }

    public Type getType() {
        return typeProperty().getValue();
    }

    public void setType(Type type) {
        typeProperty().setValue(type);
    }

    public Property<LocalDateTime> dateProperty() {
        return date;
    }

    public LocalDateTime getDate() {
        return dateProperty().getValue();
    }

    public void setDate(LocalDateTime date) {
        dateProperty().setValue(date);
    }


    public void setComment(String comment) {
        this.comment.setValue(comment);
    }


    /*public TourDifficulty getTourDifficulty() {
        return tourDifficulty.get();
    }

    public void setTourDifficulty(TourDifficulty tourDifficulty) {
        this.tourDifficulty.set(tourDifficulty);
    }

    public ObjectProperty<TourDifficulty> tourDifficultyProperty() {
        return tourDifficulty;
    }*/

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
/*
    public Property<String> () {
        return message;
    }

    public String getMessage() {
        return messageProperty().getValue();
    }

    public void setMessage(String message) {
        messageProperty().setValue(message);
    }*/
}

