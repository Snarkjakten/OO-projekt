package Projectiles;

import Move.Direction;

public class Asteroids extends Projectiles {
    private int damage;

    public Asteroids(int diameter, double speed, double xPos, double yPos, Direction direction) {
        super(10, 100, 10, 10, Direction.EAST);
        this.damage = 20;
    }
}
