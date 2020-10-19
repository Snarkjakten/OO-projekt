package Model.Entities.Player;

import Interfaces.ICollisionObserver;
import Model.Entities.HitBox;
import Model.Entities.Projectiles.Asteroid;
import Model.Entities.Projectiles.HealthPowerUp;
import Model.Entities.Projectiles.ShieldPowerUp;
import Model.Movement.AbstractGameObject;

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
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    /**
     * Updates velocity
     *
     * @author Irja Vuorela
     */
    public void updateVelocity() {
        // Set velocity for all hitBoxes in the list
        for (HitBox hitBox : getHitBoxes()) {
            hitBox.setVelocity(up, down, left, right, getSpeed());
        }
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

    protected void setHp(int hp) {
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

    protected void gainShield() {
        this.nrOfShields += 1;
    }

    protected void loseShield() {
        if (this.nrOfShields > 0) this.nrOfShields -= 1;
        else this.nrOfShields = 0;
    }

    /**
     * Acts upon the collision based on instance of projectile
     *
     * @param gameObject
     * @author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public void actOnEvent(AbstractGameObject gameObject) {
        if (gameObject instanceof Asteroid)
            if (this.nrOfShields > 0) loseShield();
            else this.setHp(getHp() - ((Asteroid) gameObject).getDamage());
        else if (gameObject instanceof ShieldPowerUp) gainShield();
        else if (gameObject instanceof HealthPowerUp)
            setHp(Math.min(getHp() + ((HealthPowerUp) gameObject).getHealth(), maxHp));
    }

    @Override
    public void actOnCollision(AbstractGameObject c) {
        c.setCollided(true);
    }
}
