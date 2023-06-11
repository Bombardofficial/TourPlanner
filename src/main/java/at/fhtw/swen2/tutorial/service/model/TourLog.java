package at.fhtw.swen2.tutorial.service.model;


import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
public class TourLog {

        private Long id;
        private LocalDateTime date;
        private String comment;
        private TourDifficulty tourDifficulty;
        private float totalTourTime;
        private int rating;

        private Tour tour;

}
