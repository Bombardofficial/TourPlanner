package at.fhtw.swen2.tutorial.service;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public interface MapQuestService {
    Image getMap(String start, String end);
    byte[] getMapByteArray(String start, String end);
}


