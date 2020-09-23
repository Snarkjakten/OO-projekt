package Entities;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;

public class LaserBeam {
    private Image[] frames;
    private double horizontal;
    private double vertical;
    private double duration;
    private boolean isHorizontal;
    private double screenHorizontalLength = 800; //TODO: Get this from model
    private double screenVerticalLength = 600; // TODO: Get this from model

    public LaserBeam(double position, double duration, boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
        setPosition(position);
        this.frames = new Image[8];
        this.duration = duration;
        initImages();
    }

    private void initImages() {
        for (int i = 0; i < 8; i++) {
            frames[i] = setImage(i);
        }
    }

    public Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    private void setPosition(double position) {
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
        Image image;
        switch (number) {
            case 0:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser01.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser01Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            case 1:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser02.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser02Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            case 2:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser03.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser03Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            case 3:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser04.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser04Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            case 4:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser05.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser05Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            case 5:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser06.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser06Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            case 6:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser07.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser07Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            case 7:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser08.png");
                    image = new Image(inputStream, 248, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("laser08Vertical.png");
                    image = new Image(inputStream, 900, 248, false, false);
                }
                return image;
            default:
                System.out.println("Failed to set image to laser");
                return null;
        }
    }

    public Image[] getImages() {
        return frames;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public double getVertical() {
        return vertical;
    }
}
