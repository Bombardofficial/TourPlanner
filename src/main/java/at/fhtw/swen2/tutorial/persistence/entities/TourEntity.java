package at.fhtw.swen2.tutorial.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Column(name = "from_property")
    private String from;
    @Column(name = "to_property")
    private String to;
    @Enumerated(EnumType.STRING)
    private TransportType transportType;
    private float distance;
    private float estimatedTime;

    //image hianyzik

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<TourLogEntity> logs;


}
