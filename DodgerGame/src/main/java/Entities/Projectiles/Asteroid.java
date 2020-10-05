package Entities.Projectiles;

import java.util.Random;

public class Asteroid extends Projectile {
    private int damage;
    private String type;

    public Asteroid() {
        super(50, 28, 28);
        initAsteroid();
    }

    public int getDamage() {
        return damage;
    }

    private void initAsteroid() {
        Random random = new Random();
        int type = random.nextInt(99);
        if (type <= 69) {
            this.damage = 20;
            this.height = 64;
            this.width = 64;
            this.speed = 200;
            this.type = "small";
        } else {
            this.damage = 35;
            this.height = 128;
            this.width = 128;
            this.speed = 100;
            this.type = "medium";
        }
    }

    public String getType() {
        return type;
    }
}
