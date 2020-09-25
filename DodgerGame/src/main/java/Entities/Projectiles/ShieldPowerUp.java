package Entities.Projectiles;

/**
 * @Author Olle Westerlund
 */
public class ShieldPowerUp extends Projectile {
    private int hitCapacity;    // Number of hits the player can take before shield is destroyed.

    public ShieldPowerUp() {
        super(4);
        hitCapacity = 1;
    }

    public int getHitCapacity() {
        return hitCapacity;
    }

}