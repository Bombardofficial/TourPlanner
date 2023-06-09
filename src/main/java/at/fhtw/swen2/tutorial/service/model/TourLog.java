package at.fhtw.swen2.tutorial.service.model;


import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
public class TourLog {

        private Long id;
      //  private LocalDateTime date;
        private String comment;
        private TourDifficulty tourDifficulty;
        private float totalTourTime;
        private int rating;

        //private Tour tour;
        private Long TourId;



}
