package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */
public class SmallAsteroid extends Projectile {
    private double damage; //The number of damage this asteroid does.

    public SmallAsteroid() {
        super(10);
        this.damage = 20;
    }

    protected double getDamage() {
        return damage;
    }
}
