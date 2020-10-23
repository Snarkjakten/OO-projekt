package Model.Entities.Projectiles;

import Model.Entities.AbstractGameObject;
import Model.Entities.HitBox;
import Model.Entities.Point2D;

import java.util.Random;

/**
 * @author Olle Westerlund
 */

public abstract class Projectile extends AbstractGameObject {
    private double xVelocity;    // positive value: right, negative value: left
    private double yVelocity;      // positive value: up, negative value: down

    public Projectile() { }

    /**
     * The method sets a random starting position for the projectile.
     *
     * @author Olle Westerlund
     */
    protected void randomPosition(double playingFieldWidth, double playingFieldHeight) {
        HitBox hitBox = getHitBoxes().get(0);
        Random randomPos = new Random();
        double xPos = 0;
        double yPos = 0;
        int side = randomPos.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                xPos = randomPos.nextDouble() * playingFieldWidth;
                yPos = playingFieldHeight + 50;
                break;
            case 1: // Right side of the screen
                xPos = playingFieldWidth + 50;
                yPos = randomPos.nextDouble() * playingFieldHeight;
                break;
            case 2: // Top of the screen
                xPos = randomPos.nextDouble() * playingFieldWidth;
                yPos = -50;
                break;
            case 3: // Left of the screen
                xPos = -50;
                yPos = randomPos.nextDouble() * playingFieldWidth;
                break;
            default:
                break;
        }
        hitBox.updateHitBoxPosition(xPos, yPos);
        randomStartVelocity(side, playingFieldWidth, playingFieldHeight);
    }

    /**
     * The method sets a random velocity and direction for the projectile.
     *
     * @param side The side of the screen that the asteroid spawns on.
     * @author Olle Westerlund
     */
    private void randomStartVelocity(int side, double playingFieldWidth, double playingFieldHeight) {
        HitBox hitBox = getHitBoxes().get(0);
        double x = 0;
        double y = 0;
        Random randomDouble = new Random();
        switch (side) {
            case 0: //Velocity from bottom
                x = randomDouble.nextDouble() * playingFieldWidth;
                if (x < hitBox.getX()) {
                    x *= -1;
                }
                y = (randomDouble.nextDouble() * (playingFieldHeight - 60)) * -1;
                break;
            case 1: //Velocity from right
                x = randomDouble.nextDouble() * (playingFieldWidth - 60) * -1;
                y = randomDouble.nextDouble() * playingFieldHeight;
                if (y < hitBox.getY()) {
                    y *= -1;
                }
                break;
            case 2: //Velocity from top
                x = randomDouble.nextDouble() * playingFieldWidth;
                if (x < hitBox.getX()) {
                    x *= -1;
                }
                y = 60 + randomDouble.nextDouble() * (playingFieldHeight - 60);
                break;
            case 3: //Velocity from left
                x = 60 + randomDouble.nextDouble() * (playingFieldWidth - 60);
                y = randomDouble.nextDouble() * playingFieldHeight;
                if (y < hitBox.getY()) {
                    y *= -1;
                }
                break;
            default:
                break;
        }
        setVelocity(x, y);
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
        this.velocity = (new Point2D(xVelocity, yVelocity).normalize());
        this.velocity = velocity.multiply(getSpeed());
    }

    /**
     * The method checks if the projectile is still on the screen.
     *
     * @return Boolean if the object is no longer on the screen.
     * @author Olle Westerlund
     */
    public boolean isNotOnPlayingField(double playingFieldWidth, double playingFieldHeight) {
        HitBox hitBox = getHitBoxes().get(0);
        boolean isStillOnX = (hitBox.getX() > -130 && hitBox.getX() < (playingFieldWidth + 130));
        boolean isStillOnY = (hitBox.getY() > -130 && hitBox.getY() < (playingFieldHeight + 130));
        return (!isStillOnX || !isStillOnY);
    }

    public void setVelocity(double xVelocity, double yVelocity) {
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
}
