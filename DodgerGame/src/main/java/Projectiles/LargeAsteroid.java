package Projectiles;

/**
 * @Author Olle Westerlund
 */

public class LargeAsteroid extends Projectile {
    private int damage;

    public LargeAsteroid() {
        super(20, 60, 10, 10);
        this.damage = 35;
    }

    public int getDamage() {
        return damage;
    }
}
