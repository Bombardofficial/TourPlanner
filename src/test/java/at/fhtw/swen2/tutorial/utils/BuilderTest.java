package at.fhtw.swen2.tutorial.utils;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.junit.jupiter.api.Test;

public class BuilderTest {

    @Test
    void testTourEntityBuilder() {
        TourEntity maxi = TourEntity.builder()
                .name("Maxi")
                .description("Tour pro")
                .from("Wien")
                .to("Graz")
                .distance(100)
                .estimatedTime(100)
                .build();
    }
    @Test
    void testTourBuilder() {
        Tour maxi = Tour.builder()
                .name("Maxi")
                .id(11L)
                .build();
    }


}
