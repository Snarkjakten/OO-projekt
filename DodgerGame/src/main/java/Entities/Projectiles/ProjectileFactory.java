package Entities.Projectiles;

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

}