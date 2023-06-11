package at.fhtw.swen2.tutorial.service.mapper;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static at.fhtw.swen2.tutorial.persistence.entities.TransportType.CAR;
import static org.junit.jupiter.api.Assertions.*;

class TourMapperTest {

    //testet, ob Eigenschaften vom TourEntity-Objekt korrekt an das Tour-Objekt übertragen werden
  // (= überprüft, ob die Id und name properties vom TourEntity-Objekts korrekt denen vom Tour-Objekts zugeordnet werden)
    @Test
    public void testFromEntity() {
        // Arrange
        TourEntity entity =  TourEntity.builder()
                .id(11l)
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .transportType(CAR)
                .build();
        TourMapper tourMapper = new TourMapper();

        // Act
        Tour tour = tourMapper.fromEntity(entity);

        // Assert
        Assertions.assertEquals(entity.getId(), tour.getId());
        Assertions.assertEquals(entity.getName(), tour.getName());
        Assertions.assertEquals(entity.getDescription(), tour.getDescription());
        Assertions.assertEquals(entity.getFrom(), tour.getFrom());
        Assertions.assertEquals(entity.getTo(), tour.getTo());
        Assertions.assertEquals(entity.getTransportType(), tour.getTransportType());
        Assertions.assertEquals(entity.getDistance(), tour.getDistance());
        Assertions.assertEquals(entity.getEstimatedTime(), tour.getEstimatedTime());
    }

    @Test
    public void testToEntity() {
        // Arrange
        Tour tour =  Tour.builder()
                .id(11l)
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .transportType(CAR)
                .build();
        TourMapper tourMapper = new TourMapper();

        // Act
        TourEntity entity = tourMapper.toEntity(tour);

        // Assert
        Assertions.assertEquals(tour.getId(), entity.getId());
        Assertions.assertEquals(tour.getName(),entity.getName());
        Assertions.assertEquals(tour.getDescription(), entity.getDescription());
        Assertions.assertEquals(tour.getFrom(), entity.getFrom());
        Assertions.assertEquals(tour.getTo(), entity.getTo());
        Assertions.assertEquals(tour.getTransportType(), entity.getTransportType());
        Assertions.assertEquals(tour.getDistance(), entity.getDistance());
        Assertions.assertEquals(tour.getEstimatedTime(), entity.getEstimatedTime());
    }
}