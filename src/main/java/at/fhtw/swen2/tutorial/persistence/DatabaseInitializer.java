package at.fhtw.swen2.tutorial.persistence;

import at.fhtw.swen2.tutorial.persistence.entities.TourDifficulty;
import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TransportType;
import at.fhtw.swen2.tutorial.persistence.repositories.TourLogRepository;
import at.fhtw.swen2.tutorial.persistence.repositories.TourRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourLogRepository tourLogRepository;


    private LocalDateTime generateRandomDate() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        LocalDate startOfYear = LocalDate.of(currentYear, 1, 1);
        long startDay = startOfYear.toEpochDay();
        long currentDay = currentDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startDay, currentDay + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        LocalTime randomTime = LocalTime.of(
                ThreadLocalRandom.current().nextInt(0, 24),
                ThreadLocalRandom.current().nextInt(0, 60),
                ThreadLocalRandom.current().nextInt(0, 60)
        );
        return randomDate.atTime(randomTime);
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        List<TourEntity> tourList = new ArrayList<>();
        tourList.add(TourEntity.builder().name("Tour_1").description("First Tour").from("Wien").to("Graz").distance(148).estimatedTime(2).transportType(TransportType.valueOf("CAR")).build());
        tourList.add(TourEntity.builder().name("Tour_2").description("Second Tour").from("Graz").to("Insbruck").distance(256).estimatedTime(5).transportType(TransportType.valueOf("TRAIN")).build());
        tourList.add(TourEntity.builder().name("Tour_3").description("Third Tour").from("Insbruck").to("Hinterbichl").distance(348).estimatedTime(28).transportType(TransportType.valueOf("WALK")).build());

        List<TourLogEntity> tourLogList = new ArrayList<>();
        tourRepository.saveAll(tourList);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        List<TourEntity> lastThreeTours = tourRepository.findTop3ByOrderByIdDesc(pageable);

        if (lastThreeTours.size() >= 1) {
            TourEntity tour1 = lastThreeTours.get(0);

            TourLogEntity tourLog1 = TourLogEntity.builder()
                    //.date(LocalDateTime.now())
                    .comment("It was quite a nice ride, however there was a 1-hour traffic jam.")
                    .difficulty(TourDifficulty.EASY)
                    .totalTourTime(2.5f)
                    .rating(4)
                    .tour(tour1)
                    .build();
            tourLogList.add(tourLog1);

            TourLogEntity tourLog2 = TourLogEntity.builder()
                    //.date(generateRandomDate())
                    .comment("The scenery was breathtaking, but the weather was a bit unpredictable.")
                    .difficulty(TourDifficulty.MODERATE)
                    .totalTourTime(3.0f)
                    .rating(4)
                    .tour(tour1)
                    .build();
            tourLogList.add(tourLog2);

            TourLogEntity tourLog3 = TourLogEntity.builder()
                  //  .date(generateRandomDate())
                    .comment("The tour was well-organized, and we enjoyed exploring the city.")
                    .difficulty(TourDifficulty.EASY)
                    .totalTourTime(2.0f)
                    .rating(5)
                    .tour(tour1)
                    .build();
            tourLogList.add(tourLog3);
        }

        if (lastThreeTours.size() >= 2) {
            TourEntity tour2 = lastThreeTours.get(1);

            TourLogEntity tourLog4 = TourLogEntity.builder()
                  //  .date(generateRandomDate())
                    .comment("It was a relaxing train journey with stunning views of the mountains.")
                    .difficulty(TourDifficulty.EASY)
                    .totalTourTime(2.5f)
                    .rating(4)
                    .tour(tour2)
                    .build();
            tourLogList.add(tourLog4);

            TourLogEntity tourLog5 = TourLogEntity.builder()
                  //  .date(generateRandomDate())
                    .comment("The train was crowded, but the journey was comfortable overall.")
                    .difficulty(TourDifficulty.MODERATE)
                    .totalTourTime(3.0f)
                    .rating(3)
                    .tour(tour2)
                    .build();
            tourLogList.add(tourLog5);

            TourLogEntity tourLog6 = TourLogEntity.builder()
                   // .date(generateRandomDate())
                    .comment("We had a great time exploring the local cuisine during the tour.")
                    .difficulty(TourDifficulty.EASY)
                    .totalTourTime(2.5f)
                    .rating(4)
                    .tour(tour2)
                    .build();
            tourLogList.add(tourLog6);
        }

        if (lastThreeTours.size() >= 3) {
            TourEntity tour3 = lastThreeTours.get(2);

            TourLogEntity tourLog7 = TourLogEntity.builder()
                  //  .date(generateRandomDate())
                    .comment("The hiking trail was challenging, but the views were worth it.")
                    .difficulty(TourDifficulty.DIFFICULT)
                    .totalTourTime(5.5f)
                    .rating(5)
                    .tour(tour3)
                    .build();
            tourLogList.add(tourLog7);

            TourLogEntity tourLog8 = TourLogEntity.builder()
                  //  .date(generateRandomDate())
                    .comment("We encountered some wildlife during the tour, which made it exciting.")
                    .difficulty(TourDifficulty.MODERATE)
                    .totalTourTime(4.0f)
                    .rating(4)
                    .tour(tour3)
                    .build();
            tourLogList.add(tourLog8);

            TourLogEntity tourLog9 = TourLogEntity.builder()
                   // .date(generateRandomDate())
                    .comment("The tour guide was knowledgeable and provided interesting information.")
                    .difficulty(TourDifficulty.EASY)
                    .totalTourTime(3.5f)
                    .rating(5)
                    .tour(tour3)
                    .build();
            tourLogList.add(tourLog9);
        }



        /*TourEntity tour1 = tourRepository.findById(5L).orElse(null);
        TourLogEntity tourLog1 = TourLogEntity.builder().id(1L).date(LocalDateTime.now()).comment("It was quite a nice ride, however there was a 1 hour traffic jam.").difficulty(TourDifficulty.EASY).totalTourTime(2.5f).rating(4).tour(tour1).build();
        tourLogList.add(tourLog1);

        TourEntity tour2 = tourRepository.findById(7L).orElse(null);
        TourLogEntity tourLog2 = TourLogEntity.builder().id(2L).date(LocalDateTime.now()).comment("It was solid, but the AC was not working.").difficulty(TourDifficulty.MODERATE).totalTourTime(3.5f).rating(3).tour(tour2).build();
        tourLogList.add(tourLog2);

        TourEntity tour3 = tourRepository.findById(11L).orElse(null);
        TourLogEntity tourLog3 = TourLogEntity.builder().id(3L).date(LocalDateTime.now()).comment("Now that was a really great tour! We really enjoyed it!").difficulty(TourDifficulty.DIFFICULT).totalTourTime(4.5f).rating(5).tour(tour3).build();
        tourLogList.add(tourLog3);*/

        tourLogRepository.saveAll(tourLogList);

    }
}