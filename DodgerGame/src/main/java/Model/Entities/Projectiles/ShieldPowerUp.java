package Model.Entities.Projectiles;

/**
 * @author Olle Westerlund
 */
public class ShieldPowerUp extends Projectile {
    private final int hitCapacity;    // Number of shields the player will get.

    public ShieldPowerUp() {
        super(200, 64, 64);
        this.hitCapacity = 1;
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
        super(speed, 64, 64);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
        setSpeed(200);
        this.hitCapacity = 1;
    }

    public int getHitCapacity() {
        return hitCapacity;
    }
}
