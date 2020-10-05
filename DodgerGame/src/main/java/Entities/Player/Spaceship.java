package Entities.Player;

import Entities.Projectiles.HealthPowerUp;
import Entities.Projectiles.MediumAsteroid;
import Entities.Projectiles.ShieldPowerUp;
import Entities.Projectiles.SmallAsteroid;
import Movement.AbstractMovable;
import javafx.geometry.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable {

    // Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    public int right = 0; // moving right increases horizontal axis value
    private static int currentShield = 0;

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

    public static void setCurrentShield(int shield) {
        currentShield = shield;
    }

    public static int getCurrentShield() {
        return currentShield;
    }

    @Override
    public void actOnCollision(Class c){
        if (c.equals(SmallAsteroid.class)) {
            if(currentShield < 1) {
                Player.setHp(Player.getHp().subtract(SmallAsteroid.getDamage()).getValue());
            } else {
                setCurrentShield(0);
            }
        }
        if (c.equals(MediumAsteroid.class)) {
            if(currentShield < 1) {
                Player.setHp(Player.getHp().subtract(MediumAsteroid.getDamage()).getValue());
            } else {
                setCurrentShield(0);
            }
        }
        if (c.equals(ShieldPowerUp.class) && currentShield < 1) {
            setCurrentShield(1);
        }
        if (c.equals(HealthPowerUp.class)) {
            if (Player.getHp().greaterThanOrEqualTo(150).getValue()) {
                Player.setHp(200);
            } else {
                Player.setHp(Player.getHp().getValue() + 50);
            }
        }
    }
}
