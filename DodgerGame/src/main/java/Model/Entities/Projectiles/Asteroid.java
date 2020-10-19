package Model.Entities.Projectiles;

import javafx.geometry.Rectangle2D;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class Asteroid extends Projectile {
    private int damage;

    public Asteroid() {
        super(50);
        initAsteroid();
    }

    /**
     * @param speed     the speed of this object.
     * @param height    the height of this object.
     * @param width     the width of this object.
     * @param xPos      the x-value of this object's position.
     * @param yPos      the y-value of this object's position.
     * @param xVelocity the x-value to calculate this object's velocity.
     * @param yVelocity the y-value to calculate this object's velocity.
     * @authors Irja & Viktor
     */
    public Asteroid(double speed, double height, double width, double xPos, double yPos, double xVelocity, double yVelocity) {
        super(speed);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
        setSpeed(200);
        setWidth(width);
        setHeight(height);
        this.damage = 20;
        getHitBoxes().get(0).setPosition(xPos, yPos);
    }

    public int getDamage() {
        return damage;
    }

    /**
     * Creates one of two random asteroids, a smaller, faster one or a bigger, slower
     * with more damage. About one of three is a bigger one and about two of three is
     * a smaller one.
     */
    private void initAsteroid() {
        Random random = new Random();
        int type = random.nextInt(100);
        if (type <= 70) {
            //Small asteroid
            this.damage = 20;
            this.height = 64;
            this.width = 64;
            this.setSpeed(200);
        } else {
            // medium asteroid
            this.damage = 35;
            this.height = 100;
            this.width = 100;
            this.setSpeed(100);
        }
    }
}
