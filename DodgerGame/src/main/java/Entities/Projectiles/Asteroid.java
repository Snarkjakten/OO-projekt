package Entities.Projectiles;

public abstract class Asteroid extends Projectile {
    private static int damage;

    public Asteroid(double speed, int damage) {
        super(speed);
        this.damage = damage;
    }

    public static int getDamage() {
        return damage;
    }
}
