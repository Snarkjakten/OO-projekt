package Game.Entities.Projectiles;

public abstract class Asteroid extends Projectile {
    private int damage;

    public Asteroid(double speed, int damage, double height, double width) {
        super(speed, height, width);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
