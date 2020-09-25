package Entities.Projectiles;

import Movement.*;
import javafx.geometry.Point2D;
import java.util.Random;

/**
 * @Author Olle Westerlund
 */

public abstract class Projectile extends AbstractMovable {
    private double speed;         // Speed of the projectile.
    private double horizontal;    // positive value: right, negative value: left
    private double vertical;      // positive value: up, negative value: down
    private double screenSizeX = 800;   // TODO: Remember to get this from model not hard code.
    private double screenSizeY = 600;   // TODO: Remember to get this from model not hard code.

    public Projectile(double speed) {
        this.speed = speed;
        randomPosition();
    }

    //TODO: Add different constructor for running scripted versions.
    public Projectile(double speed, String scriptVersion) {

    }

    /**
     * @Author Olle Westerlund
     * The method sets a random starting position for the projectile.
     */
    private void randomPosition() {
        Random randomPos = new Random();
        double xPos = 0;
        double yPos = 0;
        int side = randomPos.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                xPos = randomPos.nextDouble() * screenSizeX;
                yPos = screenSizeY + 50;
                this.position = new Point2D(xPos, yPos);
                break;
            case 1: // Right side of the screen
                xPos = 850;
                yPos = randomPos.nextDouble() * screenSizeY;
                this.position = new Point2D(xPos, yPos);
                break;
            case 2: // Top of the screen
                xPos = randomPos.nextDouble() * screenSizeX;
                yPos = -50;
                this.position = new Point2D(xPos, yPos);
                break;
            case 3: // Left of the screen
                xPos = -50;
                yPos = randomPos.nextDouble() * screenSizeY;
                this.position = new Point2D(xPos, yPos);
                break;
            default:
                System.out.println("Error in randomPosition");
                break;
        }
        randomStartVelocity(side);
    }

    /**
     * @Author Olle Westerlund
     * The method sets a random velocity and direction for the projectile.
     * @param side The side of the screen that the asteroid spawns on.
     */
    private void randomStartVelocity(int side) {
        double xPos = 0;
        double yPos = 0;
        Random randomDouble = new Random();
        switch (side) {
            case 0: //Velocity from bottom
                xPos = randomDouble.nextDouble() * screenSizeX;
                if (xPos < this.position.getX()) {
                    xPos *= -1;
                }
                yPos = (randomDouble.nextDouble() * (screenSizeY - 60)) * -1;
                break;
            case 1: //Velocity from right
                xPos = randomDouble.nextDouble() * (screenSizeX - 60) * -1;
                yPos = randomDouble.nextDouble() * screenSizeY;
                if (yPos < this.position.getY()) {
                    yPos *= -1;
                }
                break;
            case 2: //Velocity from top
                xPos = randomDouble.nextDouble() * screenSizeX;
                if (xPos < this.position.getX()) {
                    xPos *= -1;
                }
                yPos = 60 + randomDouble.nextDouble() * (screenSizeY - 60);
                break;
            case 3: //Velocity from left
                xPos = 60 + randomDouble.nextDouble() * (screenSizeX - 60);
                yPos = randomDouble.nextDouble() * screenSizeY;
                if (yPos < this.position.getY()) {
                    yPos *= -1;
                }
                break;
            default:
                System.out.println("Something wrong in randomVelocity");
                break;
        }
        setHorizontal(xPos);
        setVertical(yPos);
    }

    /**
     * @Author Irja Vuorela
     */
    @Override
    public void move() {
        updateVelocity();
        updatePosition();
    }

    /**
     * @Author Irja Vuorela
     */
    public void updateVelocity() {
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        this.velocity = velocity.multiply(this.speed);
    }

    /**
     * The method checks if the projectile is still on the screen.
     * @Author Olle Westerlund
     * @return Boolean if the object is no longer on the screen.
     */
    public boolean isNotOnScreen() {
        boolean isStillOnX = (position.getX() > -70 && position.getX() < (screenSizeX + 70));
        boolean isStillOnY = (position.getY() > -70 && position.getY() < (screenSizeY + 70));
        return (!isStillOnX || !isStillOnY);
    }

    public double getSpeed() {
        return speed;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public double getVertical() {
        return vertical;
    }

    public void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    public void setVertical(double vertical) {
        this.vertical = vertical;
    }
}
