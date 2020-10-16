package Model.Entities.Projectiles;

/**
 * @author Olle Westerlund
 */
public class ShieldPowerUp extends Projectile {
    private int hitCapacity;    // Number of shields the player will get.

    public ShieldPowerUp() {
        super(200, 64, 64);
        hitCapacity = 1;
    }

    public int gainShield() {
        return hitCapacity;
    }
}
