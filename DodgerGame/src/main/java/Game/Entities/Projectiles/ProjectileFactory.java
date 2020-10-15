package Game.Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */

public abstract class ProjectileFactory {

    public static Projectile createSmallAsteroid() {
        return new SmallAsteroid();
    }

    public static Projectile createMediumAsteroid() {
        return new MediumAsteroid();
    }

    public static Projectile createHealthPowerUp() {
        return new HealthPowerUp();
    }

    public static Projectile createShieldPowerUp() {
        return new ShieldPowerUp();
    }

    public static Projectile createDebuff() {
        return new SlowDebuff();
    }

}
