package Game.Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */
public class HealthPowerUp extends Projectile{
    private int healthPoint;  // Percent of health the player gets back.

    public HealthPowerUp() {
        super(400, 32,32);
        healthPoint = 50;
    }

    /**
     * The method returns the amount of health the player will gain depending on the total health pool.
     * @param totalHealthPool The maximum amount of health the player can have.
     * @return The amount of health the player will gain.
     */
    public int gainHealth(int totalHealthPool) {
        return healthPoint;
    }
}
