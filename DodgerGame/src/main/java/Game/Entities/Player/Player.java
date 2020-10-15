package Game.Entities.Player;

import Game.Entities.Projectiles.Asteroid;
import Game.Entities.Projectiles.HealthPowerUp;
import Game.Entities.Projectiles.ShieldPowerUp;
import Game.Movement.AbstractGameObject;
import Interfaces.ICollisionObserver;

import java.util.ArrayList;
import java.util.List;

public class Player implements ICollisionObserver {

    private int maxHp;
    private List<Spaceship> spaceships;
    private int hp;

    private int points;
    private int nrOfShields;

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

    public void gainShield() {
        this.nrOfShields += 1;
    }

    public void loseShield() {
        if (this.nrOfShields > 0) {
            this.nrOfShields -= 1;
        } else {
            this.nrOfShields = 0;
        }
    }


    @Override
    public void actOnEvent(AbstractGameObject gameObject) {
        if (gameObject instanceof Asteroid) {
            if (nrOfShields > 0) {
                loseShield();
            } else {
                this.setHp(getHp() - ((Asteroid) gameObject).getDamage());
            }
        } else if (gameObject instanceof ShieldPowerUp) {
            gainShield();
        } else if (gameObject instanceof HealthPowerUp) {
            if (getHp() + ((HealthPowerUp) gameObject).getHealth() > maxHp) {
                setHp(maxHp);
            } else {
                setHp(getHp() + ((HealthPowerUp) gameObject).getHealth());
            }
        }
    }
}
