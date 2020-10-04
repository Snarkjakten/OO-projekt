package Entities.Projectiles;

public abstract class Asteroid extends Projectile {
    private static int damage;

    public Asteroid(double speed, int damage, double height, double width) {
        super(speed, height, width);
        this.damage = damage;
    }

    public static int getDamage() {
        return damage;
    }
}
