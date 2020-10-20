package Model.Entities.Projectiles;

import Model.Entities.HitBox;
import Model.GameWorld;
import Model.Movement.AbstractGameObject;
import Model.Point2D;
import java.util.Random;

/**
 * @author Olle Westerlund
 */

public abstract class Projectile extends AbstractGameObject {
    private double xVelocity;    // positive value: right, negative value: left
    private double yVelocity;      // positive value: up, negative value: down
    private final double horizontalMapSize = GameWorld.getInstance().getPlayingFieldWidth();
    private final double verticalMapSize = GameWorld.getInstance().getPlayingFieldHeight();

    public Projectile(double speed, double width, double height) {
        super(width, height);
        setSpeed(speed);
        getHitBoxes().add(new HitBox(0, 0, width, height));
        randomPosition();
    }

    /**
     * @author Olle Westerlund
     * The method sets a random starting position for the projectile.
     */
    private void randomPosition() {
        HitBox hitBox = getHitBoxes().get(0);
        Random randomPos = new Random();
        double xPos = 0;
        double yPos = 0;
        int side = randomPos.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                xPos = randomPos.nextDouble() * horizontalMapSize;
                yPos = verticalMapSize + 50;
                break;
            case 1: // Right side of the screen
                xPos = horizontalMapSize + 50;
                yPos = randomPos.nextDouble() * verticalMapSize;
                break;
            case 2: // Top of the screen
                xPos = randomPos.nextDouble() * horizontalMapSize;
                yPos = -50;
                break;
            case 3: // Left of the screen
                xPos = -50;
                yPos = randomPos.nextDouble() * verticalMapSize;
                break;
            default:
                break;
        }
        hitBox.setPosition(xPos, yPos);
        randomStartVelocity(side);
    }


    // todo: kolla om vi kan undvika projektiler som missar skärmen
    /**
     * @author Olle Westerlund
     * The method sets a random velocity and direction for the projectile.
     * @param side The side of the screen that the asteroid spawns on.
     */
    private void randomStartVelocity(int side) {
        HitBox hitBox = getHitBoxes().get(0);
        double xPos = 0;
        double yPos = 0;
        Random randomDouble = new Random();
        switch (side) {
            case 0: //Velocity from bottom
                xPos = randomDouble.nextDouble() * horizontalMapSize;
                if (xPos < hitBox.getXPos()) {
                    xPos *= -1;
                }
                yPos = (randomDouble.nextDouble() * (verticalMapSize - 60)) * -1;
                break;
            case 1: //Velocity from right
                xPos = randomDouble.nextDouble() * (horizontalMapSize - 60) * -1;
                yPos = randomDouble.nextDouble() * verticalMapSize;
                if (yPos < hitBox.getYPos()) {
                    yPos *= -1;
                }
                break;
            case 2: //Velocity from top
                xPos = randomDouble.nextDouble() * horizontalMapSize;
                if (xPos < hitBox.getXPos()) {
                    xPos *= -1;
                }
                yPos = 60 + randomDouble.nextDouble() * (verticalMapSize - 60);
                break;
            case 3: //Velocity from left
                xPos = 60 + randomDouble.nextDouble() * (horizontalMapSize - 60);
                yPos = randomDouble.nextDouble() * verticalMapSize;
                if (yPos < hitBox.getYPos()) {
                    yPos *= -1;
                }
                break;
            default:
                break;
        }
        setXVelocity(xPos);
        setYVelocity(yPos);
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
     * @author Irja Vuorela
     */

    public void updateVelocity() {
        this.velocity = (new Point2D(xVelocity, yVelocity).normalize());
        this.velocity = velocity.multiply(getSpeed());
    }

    /**
     * @author Olle Westerlund
     * The method checks if the projectile is still on the screen.
     * @return Boolean if the object is no longer on the screen.
     */
    public boolean isNotOnScreen() {
        HitBox hitBox = getHitBoxes().get(0);
        boolean isStillOnX = (hitBox.getXPos() > -130 && hitBox.getXPos() < (horizontalMapSize + 130));
        boolean isStillOnY = (hitBox.getYPos() > -130 && hitBox.getYPos() < (verticalMapSize + 130));
        return (!isStillOnX || !isStillOnY);
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
}
