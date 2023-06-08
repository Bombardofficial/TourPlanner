package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import javafx.beans.property.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LogEntry {
    public static enum Type {
        ERROR,
        WARNING,
        INFO
    }

    Property<Type> type = new SimpleObjectProperty<>(this, "type");
    Property<LocalDateTime> date = new SimpleObjectProperty<>(this, "date");
    Property<String> systemName = new SimpleStringProperty(this, "systemName");
    Property<String> comment = new SimpleStringProperty(this, "comment");

    public LogEntry(Type type, String systemName, String comment) {
        this.type.setValue(type);
        this.date.setValue(LocalDateTime.now());
        this.systemName.setValue(systemName);
        this.comment.setValue(comment);
    }

    public Property<Type> typeProperty() {
        return type;
    }
/*
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

    public Property<String> systemNameProperty() {
        return systemName;
    }

    public String getSystemName() {
        return systemNameProperty().getValue();
    }

    public void setSystemName(String systemName) {
        systemNameProperty().setValue(systemName);
    }

    public Property<String> messageProperty() {
        return message;
    }

    public String getMessage() {
        return messageProperty().getValue();
    }

    public void setMessage(String message) {
        messageProperty().setValue(message);
    }*/
}
