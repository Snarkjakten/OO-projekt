package Game.Entities.Projectiles;

/**
 * @Author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private int slowSpeed;

    public SlowDebuff() {
        super(400, 32, 32);
        slowSpeed = 100;
    }

    public int getSlowSpeed() { return slowSpeed; }
}
