package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.TourRepository;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.mapper.TourMapper;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourMapper tourMapper;

    @Override
    public List<Tour> getTourList() {
        return tourMapper.fromEntity(tourRepository.findAll());
    }

    @Override
    public Tour addNew(Tour tour) {
        if (tour == null){
            return null;
        }
        TourEntity entity = tourRepository.save(tourMapper.toEntity(tour));
        return tourMapper.fromEntity(entity);
    }
/*
    @Override
    public TourLog addTourLog(TourLog tourLog) {
        if (tourLog == null){
            return null;
        }
        TourLogEntity entity = tourRepository.save(tourMapper.toEntity(tourLog));
        return tourMapper.fromEntity(entity);
    }
*/
    @Override
    public void deleteTour(Tour tour) {
        tourRepository.delete(tourMapper.toEntity(tour));

    }


}
