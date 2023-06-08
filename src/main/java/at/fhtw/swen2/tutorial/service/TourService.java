package at.fhtw.swen2.tutorial.service;

import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;

import java.util.List;

public interface TourService {

    List<Tour> getTourList();

    Tour addNew(Tour tour);

  //  TourLog addTourLog(TourLog tourLog);

    void deleteTour(Tour tour);

   // public void addTourLog(Tour tour, TourLog tourLog);


}
