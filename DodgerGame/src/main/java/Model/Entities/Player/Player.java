package Model.Entities.Player;

import Interfaces.ITimeObserver;
import Model.Entities.Projectiles.Asteroid;
import Model.Entities.Projectiles.HealthPowerUp;
import Model.Entities.Projectiles.ShieldPowerUp;
import Model.Entities.Projectiles.SlowDebuff;
import Model.Movement.AbstractGameObject;
import Interfaces.ICollisionObserver;

import java.util.ArrayList;
import java.util.List;

public class Player implements ICollisionObserver {

    private int maxHp;
    private List<Spaceship> spaceships;
    private int hp;

    private int points;
    private int nrOfShields;

    // todo: add authors
    public Player() {
        this.spaceships = new ArrayList<>();
        this.nrOfShields = 0;
        this.points = 0;
        maxHp = 200;
        this.hp = maxHp;
    }

    public Player(List<Spaceship> spaceships, int nrOfShields, int points, int hp) {
        this.spaceships = spaceships;
        this.nrOfShields = nrOfShields;
        this.points = points;
        maxHp = 200;
        this.hp = hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Spaceship> getSpaceships() {
        return spaceships;
    }

    public void setSpaceships(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    public void setNrOfShields(int nrOfShields) {
        this.nrOfShields = nrOfShields;
    }

    public int getNrOfShields() {
        return nrOfShields;
    }

    /**
     * @authors Irja & Viktor
     * @param hitCapacity amount of collisions a single shield power up can block
     */
    public void gainShield(int hitCapacity) {
        this.nrOfShields += hitCapacity;
    }

    /**
     * @authors Irja & Viktor
     * @param healingValue amount of healing from one health power up
     */
    public void gainHealth(int healingValue) {
        if (getHp() + healingValue > maxHp) {
            setHp(maxHp);
        } else {
            setHp(getHp() + healingValue);
        }
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
     * @authors Viktor, Olle, Tobias
     * @param gameObject an object from the game objects list in the game loop
     */
    @Override
    public void actOnCollisionEvent(AbstractGameObject gameObject) {
        if (gameObject instanceof Asteroid) {
            if (nrOfShields > 0) {
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
            for (Spaceship spaceship : spaceships) {
                spaceship.setSpeed(slowSpeedFactor * spaceship.getSpeed());
            }
        }
    }
}
