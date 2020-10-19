package Model.Entities.Player;

import Interfaces.ICollisionObserver;
import Model.Entities.HitBox;
import Model.Entities.Projectiles.Asteroid;
import Model.Entities.Projectiles.HealthPowerUp;
import Model.Entities.Projectiles.ShieldPowerUp;
import Model.Movement.AbstractGameObject;
import javafx.geometry.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractGameObject implements ICollisionObserver {

    private final int maxHp;
    private int hp;
    private int points;
    private int nrOfShields;

    // Game.Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    private int right = 0; // moving right increases horizontal axis value

    public Spaceship(double xPos, double yPos, double width, double height) {
        setWidth(width);
        setHeight(height);
        getHitBoxes().add(new HitBox(xPos, yPos, width, height));
        this.nrOfShields = 0;
        this.points = 0;
        maxHp = 200;
        this.hp = maxHp;
        setSpeed(300);
    }

    @Override
    public void move(double deltaTime) {
        //System.out.println("First: " + this.velocity);
        updateVelocity();
        //System.out.println("Before: " + this.velocity);
        updatePosition(deltaTime);
        //System.out.println("After: " + this.velocity);
    }

    /**
     * Updates velocity
     *
     * @author Irja Vuorela
     */
    public void updateVelocity() {
        // Stop if moving in two opposite direction simultaneously
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity = velocity.multiply(getSpeed());
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

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    protected int getPoints() {
        return points;
    }

    protected void setPoints(int points) {
        this.points = points;
    }

    public int getNrOfShields() {
        return nrOfShields;
    }

    public void gainShield() {
        this.nrOfShields += 1;
    }

    public void gainHealth(int hp) {
        this.hp = hp;
    }

    protected void loseShield() {
        if (this.nrOfShields > 0) this.nrOfShields -= 1;
        else this.nrOfShields = 0;
    }

    @Override
    public void actOnCollision(AbstractGameObject c) {
        c.setCollided(true);
    }

    /**
     * Acts upon the collision based on instance of projectile
     *
     * @param gameObject
     * @author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public void actOnCollisionEvent(AbstractGameObject gameObject) {
        if (gameObject instanceof Asteroid)
            if (this.nrOfShields > 0) loseShield();
            else this.setHp(getHp() - ((Asteroid) gameObject).getDamage());
        else if (gameObject instanceof ShieldPowerUp) gainShield();
        else if (gameObject instanceof HealthPowerUp)
            setHp(Math.min(getHp() + ((HealthPowerUp) gameObject).getHealingValue(), maxHp));
    }
}
