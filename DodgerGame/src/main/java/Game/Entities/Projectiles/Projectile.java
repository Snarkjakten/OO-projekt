package Game.Entities.Projectiles;

import Game.Entities.Player.HitBox;
import Game.Movement.*;

import java.util.Random;

/**
 * @author Olle Westerlund
 */

public abstract class Projectile extends AbstractGameObject {
    private double horizontal;    // positive value: right, negative value: left
    private double vertical;      // positive value: up, negative value: down
    private final double screenSizeX = 800;   // TODO: Remember to get this from model not hard code.
    private final double screenSizeY = 600;   // TODO: Remember to get this from model not hard code.

    public Projectile(double speed, double width, double height) {
        setSpeed(speed);
        setWidth(width);
        setHeight(height);
        getHitBoxes().add(new HitBox(0, 0, width, height));
        randomPosition();
    }

    //TODO: Add different constructor for running scripted versions.
    public Projectile(double speed, String scriptVersion) {

    }

    /**
     * @author Olle Westerlund
     * The method sets a random starting position for the projectile.
     */
    private void randomPosition() {
        HitBox hitBox = getHitBoxes().get(0);
        Random randomPos = new Random();
        double xPos;
        double yPos;
        int side = randomPos.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                xPos = randomPos.nextDouble() * screenSizeX;
                yPos = screenSizeY + 50;
                hitBox.updatePosition(xPos, yPos);
                break;
            case 1: // Right side of the screen
                xPos = 850;
                yPos = randomPos.nextDouble() * screenSizeY;
                hitBox.updatePosition(xPos, yPos);
                break;
            case 2: // Top of the screen
                xPos = randomPos.nextDouble() * screenSizeX;
                yPos = -50;
                hitBox.updatePosition(xPos, yPos);
                break;
            case 3: // Left of the screen
                xPos = -50;
                yPos = randomPos.nextDouble() * screenSizeY;
                hitBox.updatePosition(xPos, yPos);
                break;
            default:
                System.out.println("Error in randomPosition");
                break;
        }
        randomStartVelocity(side);
    }

    /**
     * @param side The side of the screen that the asteroid spawns on.
     * @author Olle Westerlund
     * The method sets a random velocity and direction for the projectile.
     */
    private void randomStartVelocity(int side) {
        HitBox hitBox = getHitBoxes().get(0);
        double xPos = 0;
        double yPos = 0;
        Random randomDouble = new Random();
        switch (side) {
            case 0: //Velocity from bottom
                xPos = randomDouble.nextDouble() * screenSizeX;
                if (xPos < hitBox.getXPos()) {
                    xPos *= -1;
                }
                yPos = (randomDouble.nextDouble() * (screenSizeY - 60)) * -1;
                break;
            case 1: //Velocity from right
                xPos = randomDouble.nextDouble() * (screenSizeX - 60) * -1;
                yPos = randomDouble.nextDouble() * screenSizeY;
                if (yPos < hitBox.getYPos()) {
                    yPos *= -1;
                }
                break;
            case 2: //Velocity from top
                xPos = randomDouble.nextDouble() * screenSizeX;
                if (xPos < hitBox.getXPos()) {
                    xPos *= -1;
                }
                yPos = 60 + randomDouble.nextDouble() * (screenSizeY - 60);
                break;
            case 3: //Velocity from left
                xPos = 60 + randomDouble.nextDouble() * (screenSizeX - 60);
                yPos = randomDouble.nextDouble() * screenSizeY;
                if (yPos < hitBox.getYPos()) {
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
     * Moves self to a new position
     *
     * @param deltaTime is the time elapsed since the last update
     * @author Irja Vuorela
     */
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    /**
     * Updates velocity
     *
     * @author Irja Vuorela
     */
    public void updateVelocity() {
        HitBox hitBox = getHitBoxes().get(0);
        hitBox.setVelocity(horizontal, vertical, getSpeed());
    }

    /**
     * The method checks if the projectile is still on the screen.
     *
     * @return Boolean if the object is no longer on the screen.
     * @author Olle Westerlund
     */
    public boolean isNotOnScreen() {
        HitBox hitBox = getHitBoxes().get(0);
        boolean isStillOnX = (hitBox.getXPos() > -70 && hitBox.getXPos() < (screenSizeX + 70));
        boolean isStillOnY = (hitBox.getYPos() > -70 && hitBox.getYPos() < (screenSizeY + 70));
        return (!isStillOnX || !isStillOnY);
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
