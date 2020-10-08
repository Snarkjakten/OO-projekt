package Entities.Player;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Spaceship> spaceships;
    private final SimpleIntegerProperty hp = new SimpleIntegerProperty(200);

    private int points;
    private int nrOfShields;

    public Player() {
        this.spaceships = new ArrayList<>();
        this.nrOfShields = 0;
        this.points = 0;
    }

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

    public void setNrOfShields(int shields) { this.nrOfShields = shields; }
}
