package Model.Entities.Projectiles;

/**
 * @author Olle Westerlund
 */
public class HealthPowerUp extends Projectile {
    private int health;  // Percent of health the player gets back.

    public HealthPowerUp() {
        super(200, 64, 64);
        health = 50;
    }

    /**
     * The method returns the amount of health the player will get.
     *
     * @return The amount of health the player will gain.
     */
    public int getHealth() {
        return health;
    }
}
