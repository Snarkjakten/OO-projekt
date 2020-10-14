package Game.Entities.Player;

import Interfaces.IObserve;
import Interfaces.IPlayerObservable;
import Interfaces.IPlayerObserver;

import java.util.ArrayList;
import java.util.List;

public class Player implements IObserve {

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
    public void actOnEvent(String event, int amount) {
        switch (event) {
            case "asteroid":
                if (nrOfShields > 0) {
                    loseShield();
                } else {
                    this.setHp(getHp() - amount);
                }
                break;
            case "shield":
                gainShield();
                break;
            case "health":
                if (getHp() + amount > maxHp) {
                    setHp(maxHp);
                } else {
                    setHp(getHp() + amount);
                }
                break;
            default:
                break;
        }
    }
}
