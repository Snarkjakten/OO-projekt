package Projectiles;

/**
 * @Author Olle Westerlund
 */

public class LargeAsteroid extends Projectile {
    private int damage; //The number of damage this asteroids does.

    public LargeAsteroid() {
        super(60);
        this.damage = 35;
    }

    public int getDamage() {
        return damage;
    }
}
