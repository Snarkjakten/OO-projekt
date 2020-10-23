package Model.Entities.Projectiles;

public class HealthPowerUp extends Projectile {

    private final int healingValue = 50;  // Amount of healing

    /**
     * @author Viktor, Olle & Irja
     */
    public HealthPowerUp(double playingFieldWidth, double playingFieldHeight) {
        randomPosition(playingFieldWidth, playingFieldHeight);
        this.setSpeed(200);
        this.setHeightHitBoxes(64);
        this.setWidthHitBoxes(64);
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
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
        setSpeed(speed);
        updateHitBoxes(xPos, yPos, 64, 64);
    }

    /**
     * The method returns the amount of health the player will get.
     *
     * @return The amount of health the player will gain.
     */
    @Override
    public int getAmount() {
        return this.healingValue;
    }

    @Override
    public String toString() {
        return "HealthPowerUp";
    }
}
