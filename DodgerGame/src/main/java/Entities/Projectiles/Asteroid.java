package Entities.Projectiles;

public abstract class Asteroid extends Projectile {
    private int damage;

    public Asteroid(double speed, int damage) {
        super(speed);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
