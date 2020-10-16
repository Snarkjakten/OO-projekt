package Game.Entities.Projectiles;

/**
 * @author Olle Westerlund
 */
public class HealthPowerUp extends Projectile {
    private final int health;  // Percent of health the player gets back.

    public HealthPowerUp() {
        super(400, 64, 64);
        health = 50;
    }

    /**
     * The method returns the amount of health the player will gain depending on the total health pool.
     *
     * @return The amount of health the player will gain.
     */
    public int getHealth() {
        return health;
    }
}
