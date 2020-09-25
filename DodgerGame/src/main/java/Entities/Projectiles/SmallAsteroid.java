package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */
public class SmallAsteroid extends Projectile {
    private int damage; //The number of damage this asteroid does.

    public SmallAsteroid() {
        super(5);
        this.damage = 20;
    }

    public int getDamage() {
        return damage;
    }
}
