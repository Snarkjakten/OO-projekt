package Entities;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;

public class LaserBeam {
    private HashMap<Integer, Image> laserMap = new HashMap<>();
    private double horizontal;
    private double vertical;
    private boolean isActive;
    private double screenHorizontalLength = 800; //TODO: Get this from model
    private double screenVerticalLength = 600; // TODO: Get this from model

    public LaserBeam(double position, boolean isHorizontal) {
        this.isActive = true;
        setPosition(position, isHorizontal);
        initImages();
    }

    private void initImages() {
        for (int i = 1; i < 9; i++) {
            Image image = setImage(i);
            laserMap.put(i, image);
        }
    }

    private void setPosition(double position, boolean isHorizontal) {
        if (isHorizontal) {
            this.horizontal = position;
            this.vertical = -50;
        } else {
            this.horizontal = -50;
            this.vertical = position;
        }
    }

    private Image setImage(int number) {
        InputStream inputStream;
        switch (number) {
            case 1:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser01.png");
                return new Image(inputStream);
            case 2:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser02.png");
                return new Image(inputStream);
            case 3:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser03.png");
                return new Image(inputStream);
            case 4:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser04.png");
                return new Image(inputStream);
            case 5:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser05.png");
                return new Image(inputStream);
            case 6:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser06.png");
                return new Image(inputStream);
            case 7:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser07.png");
                return new Image(inputStream);
            case 8:
                inputStream = getClass().getClassLoader().getResourceAsStream("laser08.png");
                return new Image(inputStream);
            default:
                System.out.println("Failed to set image to laser");
                return null;
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public HashMap<Integer, Image> getLaserMap() {
        return laserMap;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public double getVertical() {
        return vertical;
    }
}
