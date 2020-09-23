package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */
public class HealthPowerUp extends Projectile{
    private double HealthPoint;  // Percent of health the player gets back.

    public HealthPowerUp() {
        super(3);
        HealthPoint = 1.25;
    }

    public double getHealthPoint() {
        return HealthPoint;
    }
}
