package Model.Entities.Projectiles;

import Model.Entities.HitBox;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class Asteroid extends Projectile {
    private int damage;
    private final int smallSize = 32;
    private final int mediumSize = 64;

    public Asteroid() {
        randomPosition();
        initAsteroid();
        getHitBoxes().add(new HitBox());
    }

    /**
     * @param speed     the speed of this asteroid.
     * @param width     the width of this asteroid.
     * @param height    the height of this asteroid.
     * @param xPos      the x-value of this asteroid's position.
     * @param yPos      the y-value of this asteroid's position.
     * @param xVelocity the x-value to calculate this asteroid's velocity.
     * @param yVelocity the y-value to calculate this asteroid's velocity.
     * @param damage    the damage of this asteroid
     * @authors Irja & Viktor
     */
    public Asteroid(double speed, double width, double height, double xPos, double yPos, double xVelocity, double yVelocity, int damage) {
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
        setSpeed(speed);
        updateHitBoxes(xPos, yPos, width, height);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    /**
     * Creates one of two random asteroids, a smaller, faster one or a bigger, slower
     * with more damage. About one of three is a bigger one and about two of three is
     * a smaller one.
     *
     * @author Olle Westerlund
     */
    private void initAsteroid() {
        Random random = new Random();
        int type = random.nextInt(100);
        if (type <= 70) {
            //Small asteroid
            this.damage = 20;
            setWidthHitBoxes(smallSize);
            setHeightHitBoxes(smallSize);
            for (HitBox hitBox : getHitBoxes())
                hitBox.updateHitBox(hitBox.getHitBox().getX(), hitBox.getHitBox().getY(), hitBox.getWidth(), hitBox.getHeight());
            setSpeed(200);

        } else {
            // medium asteroid
            this.damage = 35;
            setWidthHitBoxes(mediumSize);
            setHeightHitBoxes(mediumSize);
            for (HitBox hitBox : getHitBoxes())
                hitBox.updateHitBox(hitBox.getHitBox().getX(), hitBox.getHitBox().getY(), hitBox.getWidth(), hitBox.getHeight());
            setSpeed(100);
        }
    }
}
