package Game.Entities.Projectiles;

/**
 * @author Olle Westerlund
 */
public class ShieldPowerUp extends Projectile {
    private final int hitCapacity;    // Number of hits the player can take before shield is destroyed.

    public ShieldPowerUp() {
        super(400, 64, 64);
        hitCapacity = 1;
    }

    public int gainShield() {
        return hitCapacity;
    }
}