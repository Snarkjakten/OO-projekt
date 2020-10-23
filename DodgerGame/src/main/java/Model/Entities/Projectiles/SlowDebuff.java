package Model.Entities.Projectiles;

/**
 * @author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private final int slowSpeedFactor;

    public SlowDebuff(double playingFieldWidth, double playingFieldHeight) {
        randomPosition(playingFieldWidth, playingFieldHeight);
        setSpeed(300);
        setWidthHitBoxes(32);
        setHeightHitBoxes(32);
        slowSpeedFactor = 50;
    }

    @Override
    public int getAmount() {
        return slowSpeedFactor;
    }

    @Override
    public String toString() {
        return "SlowDebuff";
    }
}
