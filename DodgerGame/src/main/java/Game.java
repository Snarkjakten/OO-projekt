import Entities.Player.Player;
import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Movement.AbstractMovable;

import java.util.ArrayList;
import java.util.List;


public class Game {
    public List<AbstractMovable> gameObjects;
    private Spaceship newSpaceship;
    private static Game instance = null;
    private Player player;

    private Game() {
        this.player = new Player();
        gameObjects = new ArrayList<>();
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
        newSpaceship = SpaceshipFactory.createSpaceship(368, 268);
        player.spaceships.add(newSpaceship);
        gameObjects.add(newSpaceship);
    }

    //@Author Tobias Engblom
    protected List<Spaceship> getSpaceships() {
        return player.spaceships;
    }

    protected Player getPlayer() {
        return player;
    }

    //@Author Tobias Engblom
    protected void wrapAround() {
        for (Spaceship spaceship : player.spaceships) {
            if (player.spaceships.size() == 1) {
                if (checkWestPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY());
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                } else if (checkNorthPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600);
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                } else if (checkEastPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY());
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                } else if (checkSouthPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64);
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                }
                // TODO The game updates so fast that it creates to many spaceships when crossing a border with just one spaceship.
                // TODO Change if?
            } else if (player.spaceships.size() < 4) {
                if (checkWestPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY());
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                } else if (checkNorthPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600);
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                } else if (checkEastPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY());
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                } else if (checkSouthPosition(spaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64);
                    player.spaceships.add(newSpaceship);
                    gameObjects.add(newSpaceship);
                    break;
                }
            }
            if (checkInactive(spaceship)) {
                player.spaceships.remove(spaceship);
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