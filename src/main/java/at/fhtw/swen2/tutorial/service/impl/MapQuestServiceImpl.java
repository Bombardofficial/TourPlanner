package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.service.MapQuestService;
import javafx.scene.image.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@Service
public class MapQuestServiceImpl implements MapQuestService {
    @Override
    public Image getMap(String start, String end) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.mapquestapi.com/staticmap/v5/map?key=3j2VZ95LwC5ZNbe2vJ4svJ1udHPDkzn2&start="+start+"&end="+end+"&size=1000,800@2x";
        byte[] mapImageByteArray = restTemplate.getForObject(url, byte[].class);
        Image image = new Image(new ByteArrayInputStream(mapImageByteArray));
        return image;
    }



}
