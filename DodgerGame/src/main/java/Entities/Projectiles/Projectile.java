package Entities.Projectiles;

import Movement.*;
import javafx.geometry.Point2D;
import java.util.Random;

/**
 * @Author Olle Westerlund
 */

public abstract class Projectile extends AbstractMovable {
    private double speed;               // Speed of the projectile.
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
     */
    private void randomPosition() {
        Random randomPos = new Random();
        double xPos = 0;
        double yPos = 0;
        int side = randomPos.nextInt(4);
        System.out.println(side);
        switch (side) {
            case 0: // Bottom of the screen
                xPos = randomPos.nextDouble() * screenSizeX;
                yPos = screenSizeY;
                this.position.add(xPos, yPos);
                randomStartVelocity(side);
                break;
            case 1: // Right side of the screen
                xPos = 800;
                yPos = randomPos.nextDouble() * screenSizeY;
                this.position.add(xPos, yPos);
                randomStartVelocity(side);
                break;
            case 2: // Top of the screen
                xPos = randomPos.nextDouble() * screenSizeX;
                yPos = 0;
                this.position.add(xPos, yPos);
                randomStartVelocity(side);
                break;
            case 3: // Left of the screen
                xPos = 0;
                yPos = randomPos.nextDouble() * screenSizeY;
                this.position.add(xPos, yPos);
                randomStartVelocity(side);
                break;
            default:
                System.out.println("Error in randomPosition");
                break;
        }
    }

    /**
     * @Author Olle Westerlund
     * @param side The side of the screen that the asteroid spawns on.
     */
    private void randomStartVelocity(int side) {
        double xPos = 0;
        double yPos = 0;
        Random randomDouble = new Random();
        switch (side) {
            case 0: //Velocity from bottom
                xPos = randomDouble.nextDouble() * screenSizeX;
                yPos = randomDouble.nextDouble() * (screenSizeY - 20);
                break;
            case 1: //Velocity from right
                xPos = randomDouble.nextDouble() * (screenSizeX - 20);
                yPos = randomDouble.nextDouble() * screenSizeY;
                break;
            case 2: //Velocity from top
                xPos = randomDouble.nextDouble() * screenSizeX;
                yPos = 20 + randomDouble.nextDouble() * (screenSizeY - 20);
                break;
            case 3: //Velocity from left
                xPos = 20 + randomDouble.nextDouble() * (screenSizeX - 20);
                yPos = randomDouble.nextDouble() * screenSizeY;
                break;
            default:
                System.out.println("Something wrong in randomVelocity");
                break;
        }
        setHorizontal(xPos);
        setVertical(yPos);
        updateVelocity();
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
    private void updateVelocity() {
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        this.velocity = velocity.multiply(this.speed);
    }

    public boolean isNotOnScreen() {
        boolean isStillOnX = (position.getX() > -150 && position.getX() < 950);
        boolean isStillOnY = (position.getY() > -150 && position.getY() < 750);
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

    private void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    private void setVertical(double vertical) {
        this.vertical = vertical;
    }
}
