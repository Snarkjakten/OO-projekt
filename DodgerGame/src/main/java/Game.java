import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private static Game instance = null;
    private final List<Spaceship> spaceships = new ArrayList<>();

    private Game() {
        initSpaceships();
    }

    //@Author Tobias Engblom
    protected static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    //@Author Tobias Engblom
    private void initSpaceships() {
        spaceships.add(SpaceshipFactory.createSpaceship(368, 268));
    }

    //@Author Tobias Engblom
    protected List<Spaceship> getSpaceships() {
        return spaceships;
    }

    //@Author Tobias Engblom
    protected void wrapAround() {
        for (Spaceship spaceship : spaceships) {
            if (spaceships.size() == 1) {
                if (checkWestPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(788, spaceship.position.getY()));
                    break;
                } else if (checkNorthPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600));
                    break;
                } else if (checkEastPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(-76, spaceship.position.getY()));
                    break;
                } else if (checkSouthPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64));
                    break;
                }
            } else if (spaceships.size() == 2 || spaceships.size() < 4) {
                if (checkWestPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(788, spaceship.position.getY()));
                    break;
                } else if (checkNorthPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600));
                    break;
                } else if (checkEastPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(-76, spaceship.position.getY()));
                    break;
                } else if (checkSouthPosition(spaceship)) {
                    spaceships.add(SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64));
                    break;
                }
            }
            if (checkInactive(spaceship)) {
                spaceships.remove(spaceship);
                break;
            }
        }
    }

    //@Author Tobias Engblom
    //Inactivates spaceship if it's outside the map
    private boolean checkInactive(Spaceship spaceship) {
        return spaceship.position.getX() < -76 || spaceship.position.getX() > 788 || spaceship.position.getY() < -64 || spaceship.position.getY() > 600;
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