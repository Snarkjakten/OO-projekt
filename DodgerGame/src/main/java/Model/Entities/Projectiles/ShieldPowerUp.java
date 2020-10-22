package Model.Entities.Projectiles;

public class ShieldPowerUp extends Projectile {
    private final int hitCapacity = 1;    // Number of shields the player will get.


    /**
     * @author Olle, Irja & Viktor
     */
    public ShieldPowerUp() {
        randomPosition();
        setWidthHitBoxes(64);
        setHeightHitBoxes(64);
        setSpeed(200);
    }

    /**
     * @param speed     the speed of this object.
     * @param xPos      the x-value of this object's position.
     * @param yPos      the y-value of this object's position.
     * @param xVelocity the x-value to calculate this object's velocity.
     * @param yVelocity the y-value to calculate this object's velocity.
     * @authors Irja & Viktor
     */
    public ShieldPowerUp(double speed, double xPos, double yPos, double xVelocity, double yVelocity) {
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
        setSpeed(speed);
        updateHitBoxes(xPos, yPos, 64, 64);
    }

    public int getHitCapacity() {
        return hitCapacity;
    }
}
