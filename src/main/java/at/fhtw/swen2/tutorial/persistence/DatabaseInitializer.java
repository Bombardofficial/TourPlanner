package at.fhtw.swen2.tutorial.persistence;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
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
        tourList.add(TourEntity.builder().id(5L).name("John").description("description").from("from").to("to").distance(10).estimatedTime(10).build());
        tourList.add(TourEntity.builder().id(7L).name("Albert").description("description").from("from").to("to").distance(10).estimatedTime(10).build());
        tourList.add(TourEntity.builder().id(11L).name("Monica").description("description").from("from").to("to").distance(10).estimatedTime(10).build());
        tourRepository.saveAll(tourList);
    }
}
