import Entities.Player.Player;
import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Movement.AbstractMovable;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private final List<AbstractMovable> gameObjects;
    private Spaceship newSpaceship;
    private static Game instance = null;
    private final Player player;

    private Game() {
        this.player = new Player();
        this.gameObjects = new ArrayList<>();
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
        newSpaceship.addObserver(player);
        player.getSpaceships().add(newSpaceship);
        gameObjects.add(newSpaceship);
        //newSpaceship.addSoundObserver();
    }

    //@Author Tobias Engblom
    protected List<Spaceship> getSpaceships() {
        return this.player.getSpaceships();
    }

    //@Author Tobias Engblom
    protected Player getPlayer() {
        return this.player;
    }

    //@Author Tobias Engblom
    protected List<AbstractMovable> getGameObjects() {
        return this.gameObjects;
    }

    //@Author Tobias Engblom
    protected void wrapAround() {
        Spaceship spaceship = player.getSpaceships().get(0);
        if (player.getSpaceships().size() == 1) {
            checkWrapAround(spaceship);
        } else if (player.getSpaceships().size() == 2) {
            checkWrapAround(spaceship, player.getSpaceships().get(1));
        }
        for (Spaceship spaceship2 : player.getSpaceships()) {
            if (checkInactive(spaceship2)) {
                player.getSpaceships().remove(spaceship2);
                gameObjects.remove(spaceship2);
                break;
            }
        }
    }

    //@Author Tobias Engblom
    private void addSpaceship(Spaceship spaceship) {
        player.getSpaceships().add(spaceship);
        gameObjects.add(spaceship);
    }

    //@Author Tobias Engblom
    private void addSpaceship(Spaceship spaceship, Spaceship otherSpaceship) {
        addSpaceship(spaceship);
        addSpaceship(otherSpaceship);
    }

    //@Author Tobias Engblom
    private void checkWrapAround(Spaceship spaceship, Spaceship nextSpaceship) {
        Spaceship otherSpaceship;
        if (checkWestPosition(spaceship) && checkWestPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY());
            otherSpaceship = SpaceshipFactory.createSpaceship(788, nextSpaceship.position.getY());
            newSpaceship.setDirection(spaceship);
            otherSpaceship.setDirection(nextSpaceship);
            addSpaceship(newSpaceship, otherSpaceship);
            newSpaceship.addObserver(player);
            otherSpaceship.addObserver(player);
        } else if (checkNorthPosition(spaceship) && checkNorthPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600);
            otherSpaceship = SpaceshipFactory.createSpaceship(nextSpaceship.position.getX(), 600);
            newSpaceship.setDirection(spaceship);
            otherSpaceship.setDirection(nextSpaceship);
            addSpaceship(newSpaceship, otherSpaceship);
            newSpaceship.addObserver(player);
            otherSpaceship.addObserver(player);
        } else if (checkEastPosition(spaceship) && checkEastPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY());
            otherSpaceship = SpaceshipFactory.createSpaceship(-76, nextSpaceship.position.getY());
            newSpaceship.setDirection(spaceship);
            otherSpaceship.setDirection(nextSpaceship);
            addSpaceship(newSpaceship, otherSpaceship);
            newSpaceship.addObserver(player);
            otherSpaceship.addObserver(player);
        } else if (checkSouthPosition(spaceship) && checkSouthPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64);
            otherSpaceship = SpaceshipFactory.createSpaceship(nextSpaceship.position.getX(), -64);
            newSpaceship.setDirection(spaceship);
            otherSpaceship.setDirection(nextSpaceship);
            addSpaceship(newSpaceship, otherSpaceship);
            newSpaceship.addObserver(player);
            otherSpaceship.addObserver(player);
        }
    }

    //@Author Tobias Engblom
    private void checkWrapAround(Spaceship spaceship) {
        if (checkWestPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY());
            newSpaceship.setDirection(spaceship);
            addSpaceship(newSpaceship);
            newSpaceship.addObserver(player);
        } else if (checkNorthPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600);
            newSpaceship.setDirection(spaceship);
            addSpaceship(newSpaceship);
            newSpaceship.addObserver(player);
        } else if (checkEastPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY());
            newSpaceship.setDirection(spaceship);
            addSpaceship(newSpaceship);
            newSpaceship.addObserver(player);
        } else if (checkSouthPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64);
            newSpaceship.setDirection(spaceship);
            addSpaceship(newSpaceship);
            newSpaceship.addObserver(player);
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