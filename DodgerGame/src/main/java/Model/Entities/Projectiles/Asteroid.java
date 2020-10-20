package Model.Entities.Projectiles;

import Model.Entities.HitBox;
import com.sun.javafx.scene.text.TextLayout;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class Asteroid extends Projectile {
    private int damage;

    public Asteroid() {
        super(50, 24, 24);
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
    public Asteroid(double speed, double width, double height, double xPos, double yPos, double xVelocity, double yVelocity) {
        super(speed, width, height);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);
        setSpeed(200);
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
            updateWidth(64);
            updateHeight(64);
            for (HitBox hitBox : getHitBoxes())
                hitBox.setHitBox(hitBox.getHitBox().getMinX(), hitBox.getHitBox().getMinY(), hitBox.getWidth(), hitBox.getHeight());
            setSpeed(200);
        } else {
            // medium asteroid
            this.damage = 35;
            updateWidth(100);
            updateHeight(100);
            for (HitBox hitBox : getHitBoxes())
                hitBox.setHitBox(hitBox.getHitBox().getMinX(), hitBox.getHitBox().getMinY(), hitBox.getWidth(), hitBox.getHeight());
            setSpeed(100);
        }
    }
}
