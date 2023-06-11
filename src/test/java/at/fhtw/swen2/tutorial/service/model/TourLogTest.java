package at.fhtw.swen2.tutorial.service.model;

import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import org.junit.jupiter.api.Test;

import static at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty.EASY;
import static at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty.MODERATE;
import static org.junit.jupiter.api.Assertions.*;

class TourLogTest {

    @Test
    public void testTourLogProperties() {

        // Arrange
        Long id = 1L;
        String comment = "This is a tour log comment";
        TourDifficulty tourDifficulty = TourDifficulty.MODERATE;
        float totalTourTime = 5;
        int rating = 1;
        Tour tour = Tour.builder()
                .name("Jovana's tour")
                .description("nice view")
                .from("Vienna")
                .to("Belgrade")
                .distance(150)
                .estimatedTime(6)
                .build();

        // Act
        TourLog tourLog = TourLog.builder()
                .comment("This is a tour log comment")
                .tourDifficulty(EASY)
                .totalTourTime(2)
                .rating(4)
                .build();
        tourLog.setId(id);
        tourLog.setComment(comment);
        tourLog.setTourDifficulty(tourDifficulty);
        tourLog.setTotalTourTime(totalTourTime);
        tourLog.setRating(rating);
        tourLog.setTour(tour);

        // Assert
        assertEquals(id, tourLog.getId());
        assertEquals(comment, tourLog.getComment());
        assertEquals(tourDifficulty, tourLog.getTourDifficulty());
        assertEquals(totalTourTime, tourLog.getTotalTourTime());
        assertEquals(rating, tourLog.getRating());
        assertEquals(tour, tourLog.getTour());
        assertEquals("This is a tour log comment", tourLog.getComment());
        assertEquals(MODERATE, tourLog.getTourDifficulty());
        assertEquals(5, tourLog.getTotalTourTime());
        assertEquals(1, tourLog.getRating());
    }

    @Test
    public void testDefaultTourLog() {
        // Arrange
        TourLog tourLog = TourLog.builder()
                .comment(null)
                .tourDifficulty(null)
                .totalTourTime(0)
                .rating(0)
                .build();

        // Assert
        assertNull(tourLog.getComment());
        assertNull(tourLog.getTourDifficulty());
        assertEquals(0, tourLog.getTotalTourTime());
        assertEquals(0, tourLog.getRating());
        assertNull(tourLog.getTour());
    }

    @Test
    public void testTourLog() {
        // Arrange
        TourLog tourLog = TourLog.builder()
                .comment("Jovi was here")
                .tourDifficulty(EASY)
                .totalTourTime(2)
                .rating(1)
                .build();

        // Assert
        assertEquals("Jovi was here", tourLog.getComment());
        assertEquals(EASY, tourLog.getTourDifficulty());
        assertEquals(2, tourLog.getTotalTourTime());
        assertEquals(1, tourLog.getRating());
        assertNull(tourLog.getTour());
    }

}