package Model.Entities.Projectiles;

/**
 * @author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private final int slowSpeedFactor;

    public SlowDebuff() {
        setSpeed(300);
        setWidthHitBoxes(32);
        setHeightHitBoxes(32);
        slowSpeedFactor = 50;
    }

    @Override
    public int getAmount() {
        return slowSpeedFactor;
    }
}
