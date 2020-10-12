package Entities.Player;

import Entities.Projectiles.*;
import Movement.AbstractMovable;
import javafx.geometry.Point2D;

import java.util.List;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable {

    // Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    private int right = 0; // moving right increases horizontal axis value

    public Spaceship(double x, double y) {
        setPosition(x, y);
        this.width = 64;
        this.height = 64;
    }

    // Move self to a new position
    // @Author Irja Vuorela
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);

        // todo: remove print
//         System.out.println("Spaceship moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // @Author Irja Vuorela
    private void updateVelocity() {
        // Stop if moving in two opposite directions simultaneously
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity = velocity.multiply(speed);
    }

    // Setters for movement directions
    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    // @Author Tobias Engblom
    // Sets this direction to the spaceships direction
    public void setDirection(Spaceship spaceship) {
        this.up = spaceship.up;
        this.down = spaceship.down;
        this.left = spaceship.left;
        this.right = spaceship.right;
    }


    /**
     * Acts upon the collision based on instance of projectile
     * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
     * @param c
     */
    @Override
    public void actOnCollision(AbstractMovable c, Player player){
        if (c instanceof Asteroid) {
            Asteroid asteroid = (Asteroid) c;
            if(player.getNrOfShields() < 1) {
                player.setHp(player.getHp().subtract(asteroid.getDamage()).getValue());
            } else {
                player.setNrOfShields(0);
            }
        }
        if (c instanceof ShieldPowerUp && player.getNrOfShields() == 0) {
            player.setNrOfShields(1);
        }
        if (c instanceof HealthPowerUp) {
            if (player.getHp().greaterThanOrEqualTo(150).getValue()) {
                player.setHp(200);
            } else {
                player.setHp(player.getHp().getValue() + 50);
            }
        }
        if (c instanceof Debuff) {
            List<Spaceship> spaceships = player.getSpaceships();

            // If there are wraparound spaceships
            for (Spaceship spaceship : spaceships) {
                spaceship.speed = 100;
            }

            player.debuffedTime = System.nanoTime();
            player.setDebuffed(true);
        }
        this.setCollided(false);
    }
}
