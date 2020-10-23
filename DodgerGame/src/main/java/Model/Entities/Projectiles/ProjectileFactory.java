package Model.Entities.Projectiles;

/**
 * A factory class that creates the different projectiles.
 *
 * @author Olle Westerlund
 */

public abstract class ProjectileFactory {

    /**
     * @return a random asteroid with random position and velocity
     * @author Olle Westerlund
     */
    public static Projectile createRandomizedAsteroid() {
        return new Asteroid();
    }

    /**
     * @authors Irja & Viktor
     */
    public static Projectile createAsteroid(double speed, double height, double width, double xPos, double yPos, double xVelocity, double yVelocity, int damage) {
        return new Asteroid(speed, width, height, xPos, yPos, xVelocity, yVelocity, damage);
    }

    /**
     * @return a health power up with random position and velocity
     * @author Olle Westerlund
     */
    public static Projectile createRandomizedHealthPowerUp() {
        return new HealthPowerUp();
    }

    /**
     * @authors Viktor & Irja
     */
    public static Projectile createHealthPowerUp(double speed, double xPos, double yPos, double xVelocity, double yVelocity) {
        return new HealthPowerUp(speed, xPos, yPos, xVelocity, yVelocity);
    }

    /**
     * @return a shield power up with random position and velocity
     * @author Olle Westerlund
     */
    public static Projectile createRandomizedShieldPowerUp() {
        return new ShieldPowerUp();
    }

    /**
     * @authors Irja & Viktor
     */
    public static Projectile createShieldPowerUp(double speed, double xPos, double yPos, double xVelocity, double yVelocity) {
        return new ShieldPowerUp(speed, xPos, yPos, xVelocity, yVelocity);
    }

    public static Projectile createSlowDebuff() {
        return new SlowDebuff();
    }


    /**
     * @return a laser beam with random starting side
     * @author Olle Westerlund
     */
    public static Projectile createRandomizedLaserBeam() {
        return new LaserBeam();
    }

    /**
     * @param side the side the laser beam will spawn on
     * @return a laser beam with a specific starting side.
     * @author Olle Westerlund
     */
    public static Projectile createLaserBeam(int side) {
        return new LaserBeam(side);
    }


}
