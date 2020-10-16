package Game.Entities.Projectiles;

/**
 * A factory class that creates the different projectiles.
 * @author Olle Westerlund
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

    public static Projectile createSlowDebuff() {
        return new SlowDebuff();
    }

}
