package at.fhtw.swen2.tutorial.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static at.fhtw.swen2.tutorial.presentation.viewmodel.LogEntry.Type.INFO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private String comment;
    @Enumerated(EnumType.STRING)
    private TourDifficulty difficulty;
    private float totalTourTime;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private TourEntity tour;

    public Object getType() {
        return INFO;
    }

    public String getSystemName() {
        return "null";
    }
}
