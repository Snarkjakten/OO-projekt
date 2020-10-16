package Game.Entities.Projectiles;

/**
 * @Author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private double slowSpeedFactor;

    public SlowDebuff() {
        super(400, 32, 32);
        slowSpeedFactor = 0.9;
    }

    public double getSlowSpeedFactor() { return slowSpeedFactor; }

    public double slowedSpeed(double speed) {
        return speed * 0.9;
    }
}