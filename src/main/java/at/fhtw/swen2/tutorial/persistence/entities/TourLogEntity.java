package at.fhtw.swen2.tutorial.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   // private LocalDateTime date;
    private String comment;
    @Enumerated(EnumType.STRING)
    private TourDifficulty difficulty;
    private float totalTourTime;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private TourEntity tour;
}
