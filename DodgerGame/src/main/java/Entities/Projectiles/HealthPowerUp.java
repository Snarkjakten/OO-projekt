package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */
public class HealthPowerUp extends Projectile{
    private static int healthPoint;  // Percent of health the player gets back.

    public HealthPowerUp() {
        super(400);
        healthPoint = 50;
    }

    /**
     * The method returns the amount of health the player will gain depending on the total health pool.
     * @param totalHealthPool The maximum amount of health the player can have.
     * @return The amount of health the player will gain.
     */
    public static int gainHealth(int totalHealthPool) {
        return totalHealthPool + healthPoint;
    }
}
