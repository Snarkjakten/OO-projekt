package Entities.Player;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Spaceship> spaceships = new ArrayList<>();
    private SimpleIntegerProperty hp = new SimpleIntegerProperty(200);
    private int points;

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
}
