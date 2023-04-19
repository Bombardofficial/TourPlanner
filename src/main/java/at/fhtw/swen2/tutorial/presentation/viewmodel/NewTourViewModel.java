package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.property.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NewTourViewModel {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty from = new SimpleStringProperty();
    private SimpleStringProperty to = new SimpleStringProperty();
    private SimpleObjectProperty<TransportType> transportType = new SimpleObjectProperty<>();
    private SimpleFloatProperty distance = new SimpleFloatProperty();
    private SimpleFloatProperty estimatedTime = new SimpleFloatProperty();

    @Autowired
    private TourService tourService;
    @Autowired
    private TourListViewModel tourListViewModel;

    private Tour tour;


    public NewTourViewModel() {

    }

    public NewTourViewModel(Tour tour) {
        this.tour = tour;
        this.id = new SimpleLongProperty(tour.getId());
        this.name = new SimpleStringProperty(tour.getName());
        this.description = new SimpleStringProperty(tour.getDescription());
        this.from = new SimpleStringProperty(tour.getFrom());
        this.to = new SimpleStringProperty(tour.getTo());
        this.transportType = new SimpleObjectProperty<>(tour.getTransportType());
        this.distance = new SimpleFloatProperty(tour.getDistance());
        this.estimatedTime = new SimpleFloatProperty(tour.getEstimatedTime());
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getFrom() {
        return from.get();
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public StringProperty fromProperty() {
        return from;
    }

    public String getTo() {
        return to.get();
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public StringProperty toProperty() {
        return to;
    }

    public float getDistance() {
        return distance.get();
    }

    public void setDistance(float distance) {
        this.distance.set(distance);
    }

    public FloatProperty distanceProperty() {
        return distance;
    }

    public float getEstimatedTime() {
        return estimatedTime.get();
    }

    public void setEstimatedTime(float estimatedTime) {
        this.estimatedTime.set(estimatedTime);
    }

    public FloatProperty estimatedTimeProperty() {
        return estimatedTime;
    }

    public TransportType getTransportType() {
        return transportType.get();
    }

    public void setTransportType(TransportType transportType) {
        this.transportType.set(transportType);
    }

    public ObjectProperty<TransportType> transportTypeProperty() {
        return transportType;
    }


    public void addNewTour() {
        Tour tour = Tour.builder()
                .name(getName())
                .description(getDescription())
                .from(getFrom())
                .to(getTo())
                .distance(getDistance())
                .estimatedTime(getEstimatedTime())
                .transportType(getTransportType())
                .build();
        tour = tourService.addNew(tour);
        tourListViewModel.addItem(tour);
    }


}
