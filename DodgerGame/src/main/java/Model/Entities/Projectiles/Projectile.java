package Model.Entities.Projectiles;

import Model.GameWorld;
import Model.Movement.AbstractGameObject;
import javafx.geometry.Point2D;
import java.util.Random;

/**
 * @author Olle Westerlund
 */

public abstract class Projectile extends AbstractGameObject {
    private double horizontal;    // positive value: right, negative value: left
    private double vertical;      // positive value: up, negative value: down
    private final double horizontalMapSize = GameWorld.getInstance().getPlayingFieldWidth();
    private final double verticalMapSize = GameWorld.getInstance().getPlayingFieldHeight();

    public Projectile(double speed, double height, double width) {
        this.setSpeed(speed);
        this.height = height;
        this.width = width;
        randomPosition();
    }

    /**
     * @author Olle Westerlund
     * The method sets a random starting position for the projectile.
     */
    private void randomPosition() {
        Random randomPos = new Random();
        double xPos = 0;
        double yPos = 0;
        int side = randomPos.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                xPos = randomPos.nextDouble() * horizontalMapSize;
                yPos = verticalMapSize + 50;
                this.position = new Point2D(xPos, yPos);
                break;
            case 1: // Right side of the screen
                xPos = horizontalMapSize + 50;
                yPos = randomPos.nextDouble() * verticalMapSize;
                this.position = new Point2D(xPos, yPos);
                break;
            case 2: // Top of the screen
                xPos = randomPos.nextDouble() * horizontalMapSize;
                yPos = -50;
                this.position = new Point2D(xPos, yPos);
                break;
            case 3: // Left of the screen
                xPos = -50;
                yPos = randomPos.nextDouble() * verticalMapSize;
                this.position = new Point2D(xPos, yPos);
                break;
            default:
                System.out.println("Error in randomPosition");
                break;
        }
        randomStartVelocity(side);
    }

    /**
     * @author Olle Westerlund
     * The method sets a random velocity and direction for the projectile.
     * @param side The side of the screen that the asteroid spawns on.
     */
    private void randomStartVelocity(int side) {
        double xPos = 0;
        double yPos = 0;
        Random randomDouble = new Random();
        switch (side) {
            case 0: //Velocity from bottom
                xPos = randomDouble.nextDouble() * horizontalMapSize;
                if (xPos < this.position.getX()) {
                    xPos *= -1;
                }
                yPos = (randomDouble.nextDouble() * (verticalMapSize - 60)) * -1;
                break;
            case 1: //Velocity from right
                xPos = randomDouble.nextDouble() * (horizontalMapSize - 60) * -1;
                yPos = randomDouble.nextDouble() * verticalMapSize;
                if (yPos < this.position.getY()) {
                    yPos *= -1;
                }
                break;
            case 2: //Velocity from top
                xPos = randomDouble.nextDouble() * horizontalMapSize;
                if (xPos < this.position.getX()) {
                    xPos *= -1;
                }
                yPos = 60 + randomDouble.nextDouble() * (verticalMapSize - 60);
                break;
            case 3: //Velocity from left
                xPos = 60 + randomDouble.nextDouble() * (horizontalMapSize - 60);
                yPos = randomDouble.nextDouble() * verticalMapSize;
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
     * Moves self to a new position
     * @author Irja Vuorela
     * @param deltaTime is the time elapsed since the last update
     */
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    /**
     * Updates velocity
     * @author Irja Vuorela
     */
    private void updateVelocity() {
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        this.velocity = velocity.multiply(this.getSpeed());
    }

    /**
     * @author Olle Westerlund
     * The method checks if the projectile is still on the screen.
     * @return Boolean if the object is no longer on the screen.
     */
    public boolean isNotOnScreen() {
        boolean isStillOnX = (position.getX() > -70 && position.getX() < (horizontalMapSize + 70));
        boolean isStillOnY = (position.getY() > -70 && position.getY() < (verticalMapSize + 70));
        return (!isStillOnX || !isStillOnY);
    }

    public void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    public void setVertical(double vertical) {
        this.vertical = vertical;
    }
}
