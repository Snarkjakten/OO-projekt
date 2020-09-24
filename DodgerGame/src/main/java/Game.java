import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance = null;
    private Spaceship spaceship = SpaceshipFactory.createSpaceship(true, 368, 268);
    private Spaceship wrapAroundSpaceship = SpaceshipFactory.createSpaceship(false, 10000, 10000);
    private List<Spaceship> spaceships = new ArrayList<>();

    private Game() {
    }

    //@Author Tobias Engblom
    protected static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.initSpaceships();
        }
        return instance;
    }

    //@Author Tobias Engblom
    private void initSpaceships() {
        spaceships.add(spaceship);
        spaceships.add(wrapAroundSpaceship);
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public Spaceship getWrapAroundSpaceship() {
        return wrapAroundSpaceship;
    }

    //@Author Tobias Engblom
    protected List<Spaceship> getSpaceships() {
        return spaceships;
    }

    //@Author Tobias Engblom
    protected void wrapAround() {
        if (spaceship.isActive() && wrapAroundSpaceship.isActive()) {
            //TODO What happens when both ships are active and crosses the "wall"?
        } else if (spaceship.isActive()) {
            activateWrapAround(spaceship, wrapAroundSpaceship);
        } else if (wrapAroundSpaceship.isActive()) {
            activateWrapAround(wrapAroundSpaceship, spaceship);
        }
        if (checkInactive(spaceship))
            spaceship.setActive(false);
        else if (checkInactive(wrapAroundSpaceship))
            wrapAroundSpaceship.setActive(false);
    }

    //@Author Tobias Engblom
    //Inactivates spaceship if it's outside the map
    private boolean checkInactive(Spaceship spaceship) {
        return spaceship.position.getX() < -76 || spaceship.position.getX() > 788 || spaceship.position.getY() < -64 || spaceship.position.getY() > 600;
    }

    //@Author Tobias Engblom
    private void activateWrapAround(Spaceship spaceship, Spaceship wrapAroundSpaceship) {
        if (checkWestPosition(spaceship)) {
            wrapAroundSpaceship.setActive(true);
            wrapAroundSpaceship.setPosition(788, spaceship.position.getY());
        } else if (checkEastPosition(spaceship)) {
            wrapAroundSpaceship.setActive(true);
            wrapAroundSpaceship.setPosition(-76, spaceship.position.getY());
        } else if (checkNorthPosition(spaceship)) {
            wrapAroundSpaceship.setActive(true);
            wrapAroundSpaceship.setPosition(spaceship.position.getX(), 600);
        } else if (checkSouthPosition(spaceship)) {
            wrapAroundSpaceship.setActive(true);
            wrapAroundSpaceship.setPosition(spaceship.position.getX(), -64);
        }
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside west wall
    private boolean checkWestPosition(Spaceship spaceship) {
        return spaceship.position.getX() <= -12;
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside north wall
    private boolean checkNorthPosition(Spaceship spaceship) {
        return spaceship.position.getY() <= 0;
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside east wall
    private boolean checkEastPosition(Spaceship spaceship) {
        return spaceship.position.getX() >= 724;
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside south wall
    private boolean checkSouthPosition(Spaceship spaceship) {
        return spaceship.position.getY() >= 536;
    }
}