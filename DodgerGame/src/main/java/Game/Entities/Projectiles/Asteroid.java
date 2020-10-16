package Game.Entities.Projectiles;

public abstract class Asteroid extends Projectile {
    private final int damage;

    public Asteroid(double speed, int damage, double width, double height) {
        super(speed, width, height);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
