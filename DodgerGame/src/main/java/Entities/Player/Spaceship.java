package Entities.Player;

import Entities.Projectiles.*;
import Movement.AbstractMovable;
import View.SoundHandler;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable implements ISpaceshipObservable {

    // Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    private int right = 0; // moving right increases horizontal axis value
    private List<IObserve> observers;

    public Spaceship(double x, double y) {
        setPosition(x, y);
        this.width = 64;
        this.height = 64;
        this.observers = new ArrayList<>();
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
     *
     * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
     * @param c
     */

    SoundHandler s = new SoundHandler();

    @Override
    public void actOnCollision(AbstractMovable c) {
        int amount = 0;
        String event = "";
        if (c instanceof Asteroid) {
            s.soundFx("src/main/resources/448226__inspectorj__explosion-8-bit-01 (2).wav");
            amount = ((Asteroid) c).getDamage();
            event = "asteroid";
        } else if (c instanceof ShieldPowerUp) {
            s.soundFx("src/main/resources/514289__mrthenoronha__alien-sound-2-8-bit (1).wav");
            event = "shield";
            amount = 1;
        } else if (c instanceof HealthPowerUp) {
            s.soundFx("src/main/resources/368691__fartbiscuit1700__8-bit-arcade-video-game-start-sound-effect-gun-reload-and-jump.wav");
            amount = ((HealthPowerUp) c).gainHealth(200);
            event = "health";
        }
        notifyObserver(event, amount);
        this.setCollided(false);
    }

    @Override
    public void notifyObserver(String event, int amount) {
        for (IObserve obs : observers) {
            obs.actOnEvent(event, amount);
        }
    }

    @Override
    public void addObserver(IObserve obs) {
        this.observers.add(obs);
    }

    @Override
    public void removeObserver(IObserve obs) {
        this.observers.remove(obs);
    }
}
