package Model.Entities.Projectiles;

/**
 * @Author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private double slowSpeedFactor;

    public SlowDebuff() {
        super(300, 32, 32);
        slowSpeedFactor = 0.75;
    }

    public double getSlowSpeedFactor() { return slowSpeedFactor; }
}
