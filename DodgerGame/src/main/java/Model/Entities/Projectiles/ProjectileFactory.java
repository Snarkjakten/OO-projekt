package Model.Entities.Projectiles;

/**
 * A factory class that creates the different projectiles.
 * @author Olle Westerlund
 */

public abstract class ProjectileFactory {

    /**
     * @author Olle Westerlund
     * @return a random asteroid with random position and velocity
     */
    public static Projectile createRandomizedAsteroid() {
        return new Asteroid();
    }

    /**
     * @authors Irja & Viktor
     */
    public static Projectile createAsteroid(double speed, double height, double width, double xPos, double yPos, double xVelocity, double yVelocity) {
        return new Asteroid(speed, width, height, xPos, yPos, xVelocity, yVelocity);
    }

    /**
     * @author Olle Westerlund
     * @return a health power up with random position and velocity
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
     * @author Olle Westerlund
     * @return a shield power up with random position and velocity
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
     * @author Olle Westerlund
     * @return a laser beam with random starting side
     */
    public static Projectile createRandomizedLaserBeam() {
        return new LaserBeam();
    }

    /**
     * @authoor Olle Westerlund
     * @param side the side the laser beam will spawn on
     * @return a laser beam with a specific starting side.
     */
    public static Projectile createLaserBeam(int side) {
        return new LaserBeam(side);
    }



}
