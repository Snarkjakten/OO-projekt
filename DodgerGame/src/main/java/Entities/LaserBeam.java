package Entities;

import Movement.AbstractMovable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Random;

/**
 * @Author Olle Westerlund
 */
public class LaserBeam extends AbstractMovable {
    private Image[] frames;
    private double horizontal;
    private double vertical;
    private double duration;
    private boolean isVertical;
    private double screenHorizontalLength = 800; //TODO: Get this from model.
    private double screenVerticalLength = 600; // TODO: Get this from model.

    public LaserBeam(double duration) {
        this.frames = new Image[8];
        this.duration = duration;
        this.speed = 100;
        randomStartPoint();
        initImages();
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    public void updateVelocity() {
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        this.velocity = velocity.multiply(this.speed);
    }

    /**
     * @Author Olle Westerlund
     * The method sets a starting side and then sets the position to go to.
     */
    private void randomStartPoint() {
        Random random = new Random();
        int side = random.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                this.isVertical = false;
                setStopPosition(0,-50);
                this.position = new Point2D(-50, screenVerticalLength + 50);
                break;
            case 1: // Right side of the screen
                this.isVertical = true;
                setStopPosition(-50,0);
                this.position = new Point2D(screenHorizontalLength + 50, -50);
                break;
            case 2: // Top of the screen
                this.isVertical = false;
                setStopPosition(0,screenVerticalLength);
                this.position = new Point2D(-50, -50);
                break;
            case 3: // Left of the screen
                this.isVertical = true;
                setStopPosition(screenHorizontalLength, 0);
                this.position = new Point2D(-50, -50);
                break;
            default:
                System.out.println("Error in randomStartPoint");
                break;
        }
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


    private void setStopPosition(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
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
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser01V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser01H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 1:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser02V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser02H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 2:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser03V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser03H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 3:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser04V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser04H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 4:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser05V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser05H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 5:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser06V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser06H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 6:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser07V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser07H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 7:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser08V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser08H.png");
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
