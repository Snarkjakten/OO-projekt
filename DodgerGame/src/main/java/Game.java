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
    private String imageName;

    private Game() {
        this.imageName = "lighter.gif";
        this.player = new Player();
        gameObjects = new ArrayList<>();
    }

    //@Author Tobias Engblom
    protected static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    //@Author Tobias Engblom
    protected void initSpaceships() {
        newSpaceship = SpaceshipFactory.createSpaceship(368, 268, imageName);
        player.spaceships.add(newSpaceship);
        gameObjects.add(newSpaceship);
    }

    //@Author Tobias Engblom
    protected List<Spaceship> getSpaceships() {
        return this.player.getSpaceships();
    }

    //@Author Tobias Engblom
    protected Player getPlayer() {
        return this.player;
    }

    protected List<AbstractMovable> getGameObjects() {
        return this.gameObjects;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    //@Author Tobias Engblom
    protected void wrapAround() {
        Spaceship spaceship = player.spaceships.get(0);
        if (player.spaceships.size() == 1) {
            checkWrapAround(spaceship);
        } else if (player.spaceships.size() == 2) {
            checkWrapAround(spaceship, player.spaceships.get(1));
        }
        for (Spaceship spaceship2 : player.spaceships) {
            if (checkInactive(spaceship2)) {
                player.spaceships.remove(spaceship2);
                gameObjects.remove(spaceship2);
                break;
            }
        }
    }

    //@Author Tobias Engblom
    private void addSpaceship(Spaceship spaceship) {
        player.spaceships.add(spaceship);
        gameObjects.add(spaceship);
    }

    //@Author Tobias Engblom
    private void addSpaceship(Spaceship spaceship, Spaceship otherSpaceship) {
        addSpaceship(spaceship);
        addSpaceship(otherSpaceship);
    }

    //@Author Tobias Engblom
    private void checkWrapAround(Spaceship spaceship, Spaceship nextSpaceship) {
        if (checkWestPosition(spaceship) && checkWestPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY(), imageName);
            otherSpaceship = SpaceshipFactory.createSpaceship(788, nextSpaceship.position.getY(), imageName);
            addSpaceship(newSpaceship, otherSpaceship);
        } else if (checkNorthPosition(spaceship) && checkNorthPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600, imageName);
            otherSpaceship = SpaceshipFactory.createSpaceship(nextSpaceship.position.getX(), 600, imageName);
            addSpaceship(newSpaceship, otherSpaceship);
        } else if (checkEastPosition(spaceship) && checkEastPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY(), imageName);
            otherSpaceship = SpaceshipFactory.createSpaceship(-76, nextSpaceship.position.getY(), imageName);
            addSpaceship(newSpaceship, otherSpaceship);
        } else if (checkSouthPosition(spaceship) && checkSouthPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64, imageName);
            otherSpaceship = SpaceshipFactory.createSpaceship(nextSpaceship.position.getX(), -64, imageName);
            addSpaceship(newSpaceship, otherSpaceship);
        }
    }

    //@Author Tobias Engblom
    private void checkWrapAround(Spaceship spaceship) {
        if (checkWestPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY(), imageName);
            addSpaceship(newSpaceship);
        } else if (checkNorthPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600, imageName);
            addSpaceship(newSpaceship);
        } else if (checkEastPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY(), imageName);
            addSpaceship(newSpaceship);
        } else if (checkSouthPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64, imageName);
            addSpaceship(newSpaceship);
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