package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */

public class MediumAsteroid extends Projectile {
    private double damage; //The number of damage this asteroids does.

    public MediumAsteroid() {
        super(200);
        this.damage = 35;
    }

    public double getDamage() {
        return damage;
    }
}
