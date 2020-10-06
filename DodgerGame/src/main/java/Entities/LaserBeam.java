package Entities;

import Movement.AbstractMovable;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * @Author Olle Westerlund
 */
public class LaserBeam extends AbstractMovable {
    private double horizontal;
    private double vertical;
    private boolean isVertical;
    private double screenHorizontalLength = 800; //TODO: Get this from model.
    private double screenVerticalLength = 600; // TODO: Get this from model.

    public LaserBeam() {
        this.speed = 100;
        randomStartPoint();
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    private void updateVelocity() {
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

    public void setStopPosition(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public double getVertical() {
        return vertical;
    }

    public boolean isVertical() {
        return isVertical;
    }
}
