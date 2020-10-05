package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */

public abstract class ProjectileFactory {

    public static Projectile createAsteroid() {
        return new Asteroid();
    }

    public static Projectile createHealthPowerUp() {
        return new HealthPowerUp();
    }

    public static Projectile createShieldPowerUp() {
        return new ShieldPowerUp();
    }

}
