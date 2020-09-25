package Entities;

import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @Author Olle Westerlund
 */
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

    /**
     * @Author Olle Westerlund
     * Loops through the array and sets an image on every position.
     */
    private void initImages() {
        for (int i = 0; i < 8; i++) {
            frames[i] = setImage(i);
        }
    }

    /**
     * @Author Olle Westerlund
     * The method decides which image to show.
     * @param time is used to calculate which index is used.
     * @return The image that is going to be displayed at the current time.
     */
    public Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * @Author Olle Westerlund
     * Sets the horizontal and vertical position of the beam depending on isHorizontal.
     * @param position The specific value where the beam should be placed.
     */
    private void setPosition(double position) {
        if (isHorizontal) {
            this.horizontal = position - (256 / 2);
            this.vertical = -50;
        } else {
            this.horizontal = -50;
            this.vertical = position - (256 / 2);
        }
    }

    /**
     * @Author Olle Westerlund
     * Sets the corresponding image depending on isHorizontal and the number passed to the method.
     * @param number Which picture number the animation needs.
     * @return The correct image and size of the image.
     */
    private Image setImage(int number) {
        InputStream inputStream;
        Image image;
        switch (number) {
            case 0:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser01.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser01Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 1:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser02.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser02Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 2:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser03.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser03Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 3:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser04.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser04Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 4:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser05.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser05Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 5:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser06.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser06Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 6:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser07.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser07Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 7:
                if (isHorizontal) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser08.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser08Vertical.png");
                    image = new Image(inputStream, 900, 256, false, false);
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
