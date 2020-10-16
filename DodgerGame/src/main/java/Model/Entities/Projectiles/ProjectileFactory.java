package Model.Entities.Projectiles;

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

    public static Projectile createScriptedAsteroid(double speed, double height, double width, double xPos, double yPos, double xVelocity, double yVelocity) {
        return new Asteroid(speed, height, width, xPos, yPos, xVelocity, yVelocity); }

}
