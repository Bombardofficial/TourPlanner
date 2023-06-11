package at.fhtw.swen2.tutorial.service.model;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import org.junit.jupiter.api.Test;

import static at.fhtw.swen2.tutorial.persistence.entities.TransportType.CAR;
import static at.fhtw.swen2.tutorial.persistence.entities.TransportType.PLANE;
import static org.junit.jupiter.api.Assertions.*;

class TourTest {


    @Test
    public void testTour() {
        // Arrange
        Tour tour = Tour.builder()
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .transportType(CAR)
                .distance(150)
                .estimatedTime(6)
                .build();

        // Assert
        assertEquals("Jovana's tour", tour.getName());
        assertEquals("nice view", tour.getDescription());
        assertEquals("Vienna", tour.getFrom());
        assertEquals("Belgrade",tour.getTo());
        assertEquals(CAR,tour.getTransportType());
        assertEquals(150, tour.getDistance());
        assertEquals(6, tour.getEstimatedTime());
    }

    @Test
    public void testTour2() {
        // Arrange
        Tour tour = Tour.builder()
                .name("Botond's tour")
                .description("no mountains in Hungary")
                .from("Belgrade")
                .to("Budapest")
                .transportType(CAR)
                .distance(150)
                .estimatedTime(10)
                .build();

        tour.setId(1L);
        tour.setName("meine Tour");
        tour.setDescription("nice view");
        tour.setFrom("Bukarest");
        tour.setTo("London");
        tour.setTransportType(PLANE);
        tour.setDistance(150);
        tour.setEstimatedTime(3);

        // Assert
        assertEquals(1L, tour.getId());
        assertEquals("meine Tour", tour.getName());
        assertEquals("nice view", tour.getDescription());
        assertEquals("Bukarest", tour.getFrom());
        assertEquals("London", tour.getTo());
        assertEquals(PLANE, tour.getTransportType());
        assertEquals(150, tour.getDistance());
        assertEquals(3, tour.getEstimatedTime());

    }

}