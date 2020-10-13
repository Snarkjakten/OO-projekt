package Entities.Projectiles;

/**
 * @Author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private int speed;

    public SlowDebuff() {
        super(400, 32, 32);
        speed = 100;
    }

    public int slowSpeed() { return speed; }
}
