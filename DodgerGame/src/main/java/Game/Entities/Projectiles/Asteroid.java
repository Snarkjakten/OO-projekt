package Game.Entities.Projectiles;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class Asteroid extends Projectile {
    private int damage;

    public Asteroid() {
        super(50, 28, 28);
        initAsteroid();
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
        int type = random.nextInt(99);
        if (type <= 69) {
            //Small asteroid
            this.damage = 20;
            this.height = 64;
            this.width = 64;
            this.speed = 200;
        } else {
            // medium asteroid
            this.damage = 35;
            this.height = 128;
            this.width = 128;
            this.speed = 100;
        }
    }
}
