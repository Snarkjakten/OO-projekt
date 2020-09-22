package Entities.Projectiles;

public class HealthPowerUp extends Projectile{
    private double HealthPoint;  // Percent of health the player gets back.

    public HealthPowerUp() {
        super(3);
        HealthPoint = 25;
    }

    public double getHealthPoint() {
        return HealthPoint;
    }
}
