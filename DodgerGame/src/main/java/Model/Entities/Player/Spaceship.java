package Model.Entities.Player;

import Model.Entities.HitBox;
import Model.Entities.Projectiles.*;
import Model.Movement.AbstractGameObject;
import Model.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractGameObject {

    private final int maxHp;
    private int hp;
    private int nrOfShields;

    // Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    private int right = 0; // moving right increases horizontal axis value

    /**
     * @param xPos   current xPosition of the spaceship
     * @param yPos   current yPosition of the spaceship
     * @param width  current width of the spaceship
     * @param height current height of the spaceship
     * @author Tobias Engblom
     */
    public Spaceship(double xPos, double yPos, double width, double height) {
        getHitBoxes().add(new HitBox(xPos, yPos, width, height));
        this.nrOfShields = 0;
        this.maxHp = 200;
        this.hp = maxHp;
        setSpeed(300);
    }

    /**
     * Move self to a new position
     *
     * @param deltaTime the length of a frame in the game loop
     * @author Irja Vuorela
     */
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    /**
     * Updates velocity. Stop if moving in two opposite directions simultaneously.
     *
     * @author Irja Vuorela
     */
    public void updateVelocity() {
        // a unit vector with your direction
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply your direction with speed
        this.velocity = velocity.multiply(getSpeed());
    }

    /**
     * @param hitCapacity amount of collisions a single shield power up can block
     * @authors Irja & Viktor
     */
    public void gainShield(int hitCapacity) {
        this.nrOfShields += hitCapacity;
    }

    /**
     * @param healingValue amount of healing from one health power up
     * @authors Irja & Viktor
     */
    public void gainHealth(int healingValue) {
        setHp(Math.min(getHp() + healingValue, maxHp));
    }

    /**
     * @author Olle Westerlund
     */
    public void loseShield() {
        if (this.nrOfShields > 0) {
            this.nrOfShields -= 1;
        } else {
            this.nrOfShields = 0;
        }
    }

    /**
     * Acts upon the collision based on instance of projectile
     *
     * @param gameObject the gameObject spaceship collided with
     *                   todo: make all gameobjects act on collision
     */
    @Override
    public void actOnCollision(AbstractGameObject gameObject) {
        gameObject.setCollided(!(gameObject instanceof LaserBeam));
    }

    /**
     * @param gameObject an object from the game objects list in the game loop
     * @authors Viktor, Olle, Tobias
     */

    public void actOnCollisionEvent(AbstractGameObject gameObject) {
        boolean gotShield = this.nrOfShields > 0;
        if (gameObject instanceof Asteroid) {
            if (gotShield) {
                loseShield();
            } else {
                this.setHp(getHp() - ((Asteroid) gameObject).getDamage());
            }
        } else if (gameObject instanceof ShieldPowerUp) {
            gainShield(((ShieldPowerUp) gameObject).getHitCapacity());
        } else if (gameObject instanceof HealthPowerUp) {
            gainHealth(((HealthPowerUp) gameObject).getHealingValue());
        } else if (gameObject instanceof SlowDebuff) {
            double slowSpeedFactor = ((SlowDebuff) gameObject).getSlowSpeedFactor();
            setSpeed(getSpeed() * slowSpeedFactor);
        } else if (gameObject instanceof LaserBeam) {
            if (gotShield) {
                loseShield();
            } else {
                this.setHp(getHp() - ((LaserBeam) gameObject).getDamage());
            }
        }
    }

    // Getters and setters --------------------------

    /**
     * Setter for direction up
     *
     * @param up
     */
    public void setUp(int up) {
        this.up = up;
    }

    /**
     * Setter for direction down
     *
     * @param down
     */
    public void setDown(int down) {
        this.down = down;
    }

    /**
     * Setter for direction left
     *
     * @param left
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * Setter for direction right
     *
     * @param right
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * @return the maximum possible health points of this spaceship
     */
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * @return the amount of health points of this spaceship
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Sets the new amount of health points to this spaceship
     *
     * @param hp the new amount of health points
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return the number of shields of this spaceship
     */
    public int getNrOfShields() {
        return this.nrOfShields;
    }

}
