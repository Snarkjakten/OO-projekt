package Model.Entities.Projectiles;

/**
 * @author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private final double slowSpeedFactor;

    public SlowDebuff() {
        setSpeed(300);
        setWidthHitBoxes(32);
        setHeightHitBoxes(32);
        slowSpeedFactor = 0.75;
    }

    public double getSlowSpeedFactor() {
        return slowSpeedFactor;
    }
}
