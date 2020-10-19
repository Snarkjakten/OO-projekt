package Model.Entities.Projectiles;

/**
 * @author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private final double slowSpeedFactor;

    public SlowDebuff() {
        super(300, 0, 0, 32, 32);
        slowSpeedFactor = 0.75;
    }

    public double getSlowSpeedFactor() {
        return slowSpeedFactor;
    }
}
