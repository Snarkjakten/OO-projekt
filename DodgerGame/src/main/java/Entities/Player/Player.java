package Entities.Player;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public List<Spaceship> spaceships;
    private static final SimpleIntegerProperty hp = new SimpleIntegerProperty(200);

    private int points;
    private int nrOfShields;

    public Player() {
        this.spaceships = new ArrayList<>();
        this.nrOfShields = 1; //TODO: Set to 0 when collision is added. Just for show atm.
        this.points = 0;
    }

    public static void setHp(int hp) {
        Player.hp.set(hp);
    }

    public static SimpleIntegerProperty getHp() {
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
}
