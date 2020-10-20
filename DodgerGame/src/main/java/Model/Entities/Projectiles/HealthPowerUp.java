package Model.Entities.Projectiles;

/**
 * @author Olle Westerlund
 */
public class HealthPowerUp extends Projectile {
    private int healingValue = 50;  // Amount of healing

    public HealthPowerUp() {
        super(200, 64, 64);
        this.healingValue = getHealingValue();
    }

    /**
     * @param speed     the speed of this object.
     * @param xPos      the x-value of this object's position.
     * @param yPos      the y-value of this object's position.
     * @param xVelocity the x-value to calculate this object's velocity.
     * @param yVelocity the y-value to calculate this object's velocity.
     * @authors Irja & Viktor
     */
    public HealthPowerUp(double speed, double xPos, double yPos, double xVelocity, double yVelocity) {
        super(speed, 64, 64);
        setPosition(xPos, yPos);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
        setSpeed(200);
        this.healingValue = getHealingValue();
    }

    /**
     * The method returns the amount of health the player will get.
     *
     * @return The amount of health the player will gain.
     */
    public int getHealingValue() {
        return healingValue;
    }
}
