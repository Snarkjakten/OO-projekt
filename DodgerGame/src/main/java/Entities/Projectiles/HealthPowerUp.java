package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */
public class HealthPowerUp extends Projectile{
    private double HealthPoint;  // Percent of health the player gets back.

    public HealthPowerUp() {
        super(3);
        HealthPoint = 0.25;
    }

    public double getHealthPoint() {
        return HealthPoint;
    }

    public int gainHealth(int totalHealthPool) {
        return (int) (totalHealthPool * this.HealthPoint);
    }
}
