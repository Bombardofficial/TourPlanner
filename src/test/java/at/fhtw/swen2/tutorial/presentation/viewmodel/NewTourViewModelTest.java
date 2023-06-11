package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.persistence.repositories.TourRepository;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewTourViewModelTest {
    /*
    @Test
    public void testNewTourViewModelConstructor() {
        // Arrange

        long id = 1;
        String name = "Tour 1";
        String description = "Description 1";
        String from = "Location A";
        String to = "Location B";
        String transportType = "Car";
        float distance = 100.0f;
        float estimatedTime = 2.5f;

        Tour tour = Tour.builder()
                .name()
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .build();


        // Act
        NewTourViewModel viewModel = new NewTourViewModel(tour);

        // Assert
        Assertions.assertEquals("Jovana");
        Assertions.assertEquals(description, viewModel.getDescription().get());
        Assertions.assertEquals(from, viewModel.getFrom().get());
        Assertions.assertEquals(to, viewModel.getTo().get());
        Assertions.assertEquals(transportType, viewModel.getTransportType().get());
        Assertions.assertEquals(distance, viewModel.getDistance().get());
        Assertions.assertEquals(estimatedTime, viewModel.getEstimatedTime().get());
    }*/
}




