package Projectiles;

/**
 * @Author Olle Westerlund
 */
public class SmallAsteroid extends Projectile {
    private int damage;

    public SmallAsteroid() {
        super(10, 100, 10, 10);
        this.damage = 20;
    }

    protected int getDamage() {
        return damage;
    }
}
