import Entities.Player.Player;
import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Movement.AbstractMovable;

import java.util.ArrayList;
import java.util.List;


public class Game {
    public List<AbstractMovable> gameObjects;
    private Spaceship newSpaceship;
    private Spaceship otherSpaceship;
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
            } else if (player.spaceships.size() == 2) {
                if (checkEastPosition(spaceship) && checkEastPosition(newSpaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(-76, newSpaceship.position.getY());
                    otherSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY());
                    player.spaceships.add(newSpaceship);
                    player.spaceships.add(otherSpaceship);
                    gameObjects.add(newSpaceship);
                    gameObjects.add(otherSpaceship);
                    break;
                } else if (checkNorthPosition(spaceship) && checkNorthPosition(newSpaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(newSpaceship.position.getX(), 600);
                    otherSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600);
                    player.spaceships.add(newSpaceship);
                    player.spaceships.add(otherSpaceship);
                    gameObjects.add(newSpaceship);
                    gameObjects.add(otherSpaceship);
                    break;
                } else if (checkWestPosition(spaceship) && checkWestPosition(newSpaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(788, newSpaceship.position.getY());
                    otherSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY());
                    player.spaceships.add(newSpaceship);
                    player.spaceships.add(otherSpaceship);
                    gameObjects.add(newSpaceship);
                    gameObjects.add(otherSpaceship);
                    break;
                } else if (checkSouthPosition(spaceship) && checkSouthPosition(newSpaceship)) {
                    newSpaceship = SpaceshipFactory.createSpaceship(newSpaceship.position.getX(), -64);
                    otherSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64);
                    player.spaceships.add(newSpaceship);
                    player.spaceships.add(otherSpaceship);
                    gameObjects.add(newSpaceship);
                    gameObjects.add(otherSpaceship);
                    break;
                }
            }
            if (checkInactive(spaceship)) {
                player.spaceships.remove(spaceship);
                gameObjects.remove(spaceship);
                break;
            }
        }
    }

    //@Author Tobias Engblom
    //Inactivates spaceship if it's outside the map
    private boolean checkInactive(Spaceship spaceship) {
        return spaceship.position.getX() < -78 || spaceship.position.getX() > 790 || spaceship.position.getY() < -66 || spaceship.position.getY() > 602;
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