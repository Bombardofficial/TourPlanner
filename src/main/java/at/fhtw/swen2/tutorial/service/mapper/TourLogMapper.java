package at.fhtw.swen2.tutorial.service.mapper;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import org.springframework.stereotype.Component;

@Component
public class TourLogMapper extends AbstractMapper<TourLogEntity, TourLog>{
    @Override
    public TourLog fromEntity(TourLogEntity entity) {
        return TourLog.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                //.TourId(entity.getTour().getId())
                .rating(entity.getRating())
              //  .dateTime(entity.getDateTime())
               // .timeInMinutes(entity.getTotalTime())
                .tourDifficulty(entity.getDifficulty())
                .id(entity.getId())
                .build();
    }

    @Override
    public TourLogEntity toEntity(TourLog tourLog) {
        return TourLogEntity.builder()
                .id(tourLog.getId())
                .comment(tourLog.getComment())
                .rating(tourLog.getRating())
              //  .dateTime((Date) dto.getDateTime())
              //  .tour(tourLogRepository.findById(tourLog.getTourId()).orElse(null))
             //   .totalTime(tourLog.getTimeInMinutes())
                .difficulty(tourLog.getTourDifficulty())
                .id(tourLog.getTourId())

                .build();
    }
}
