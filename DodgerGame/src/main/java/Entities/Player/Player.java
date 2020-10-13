package Entities.Player;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Player implements IObserve {

    private final int maxHp;
    private List<Spaceship> spaceships;
    private final SimpleIntegerProperty hp;

    private int points;
    private int nrOfShields;

    private Boolean slowDebuffed = false;
    private long slowDebuffedTime;

    public Player() {
        this.spaceships = new ArrayList<>();
        this.nrOfShields = 0;
        this.points = 0;
        maxHp = 200;
        this.hp = new SimpleIntegerProperty(maxHp);
    }

    public Boolean getSlowDebuffed(){
        return slowDebuffed;
    }

    public void setSlowDebuffed(Boolean slowDebuffed) {this.slowDebuffed = slowDebuffed; }

    public void setHp(int hp) {
        this.hp.set(hp);
    }

    public SimpleIntegerProperty getHp() {
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

    public int getNrOfShields() {
        return nrOfShields;
    }

    public void gainShield() {
        this.nrOfShields += 1;
    }

    public long getSlowDebuffedTime() {
        return slowDebuffedTime;
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
                    this.setHp(getHp().getValue() - amount);
                }
                break;
            case "shield":
                gainShield();
                break;
            case "health":
                if (getHp().getValue() + amount > maxHp) {
                    setHp(maxHp);
                } else {
                    setHp(getHp().getValue() + amount);
                }
                break;
            case "slowDebuff":
                List<Spaceship> spaceships = getSpaceships();

                // If there are wraparound spaceships
                for (Spaceship spaceship : spaceships) {
                    spaceship.speed = amount;
                }

                slowDebuffedTime = System.nanoTime();
                setSlowDebuffed(true);
                break;
            default:
                break;
        }
    }
}
