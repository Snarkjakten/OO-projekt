package Projectiles;

/**
 * @Author Olle Westerlund
 */

public class LargeAsteroid extends Projectile {
    private double damage; //The number of damage this asteroids does.

    public LargeAsteroid() {
        super(60);
        this.damage = 35;
    }

    public double getDamage() {
        return damage;
    }
}
