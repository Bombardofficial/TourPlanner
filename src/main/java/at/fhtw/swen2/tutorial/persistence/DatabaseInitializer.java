package at.fhtw.swen2.tutorial.persistence;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.persistence.repositories.TourRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    private TourRepository tourRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<TourEntity> tourList = new ArrayList<>();
        tourList.add(TourEntity.builder().id(5L).name("Tour_1").description("First Tour").from("Wien").to("Graz").distance(148).estimatedTime(2).transportType(TransportType.valueOf("CAR")).build());
        tourList.add(TourEntity.builder().id(7L).name("Tour_2").description("Second Tour").from("Graz").to("Insbruck").distance(256).estimatedTime(5).transportType(TransportType.valueOf("TRAIN")).build());
        tourList.add(TourEntity.builder().id(11L).name("Tour_3").description("Third Tour").from("Insbruck").to("Hinterbichl").distance(348).estimatedTime(28).transportType(TransportType.valueOf("WALK")).build());
        tourRepository.saveAll(tourList);
    }
}
