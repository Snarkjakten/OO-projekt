package Entities.Projectiles;

public class HealthPowerUp extends Projectile{
    private double HealthPoint;

    public HealthPowerUp() {
        super(5);
        HealthPoint = 50;
    }

    public double getHealthPoint() {
        return HealthPoint;
    }
}
