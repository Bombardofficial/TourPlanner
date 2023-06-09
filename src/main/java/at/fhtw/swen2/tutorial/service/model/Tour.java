package at.fhtw.swen2.tutorial.service.model;

import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;


@Data
@Builder
public class Tour {
    private Long id;
    private String name;
    private String description;
    private String from;
    private String to;
    private TransportType transportType;
    private float distance;
    private float estimatedTime;

   // private List<TourLog> tourLogs;

}

