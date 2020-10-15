package Game;

import Game.Entities.Player.Player;
import Game.Entities.Player.Spaceship;
import Game.Entities.Player.SpaceshipFactory;
import Game.Movement.AbstractGameObject;

import java.util.ArrayList;
import java.util.List;


public class GameWorld {
    private final List<AbstractGameObject> gameObjects;
    private Spaceship newSpaceship;
    private static GameWorld instance = null;
    private Player player;
    private boolean isGameOver;

    private static final double playingFieldWidth = 800;
    private static final double playingFieldHeight = 600;

    private GameWorld() {
        this.isGameOver = false;
        this.player = new Player();
        this.gameObjects = new ArrayList<>();
        initSpaceships();
    }

    public void createNewGameWorld(){
        reset();

    }
    private void reset() {
        this.isGameOver = false;
        this.player = new Player();
        this.gameObjects.clear();
        initSpaceships();
    }

    //@Author Tobias Engblom
    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    //@Author Tobias Engblom
    private void initSpaceships() {
        newSpaceship = SpaceshipFactory.createSpaceship(368, 268);
        player.getSpaceships().add(newSpaceship);
        gameObjects.add(newSpaceship);
        //newSpaceship.addSoundObserver();
    }

    //@Author Tobias Engblom
    public List<Spaceship> getSpaceships() {
        return this.player.getSpaceships();
    }

    //@Author Tobias Engblom
    public Player getPlayer() {
        return this.player;
    }

    //@Author Tobias Engblom
    public List<AbstractGameObject> getGameObjects() {
        return this.gameObjects;
    }

    //@Author Tobias Engblom
    public void wrapAround() {
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
        } else if (checkNorthPosition(spaceship) && checkNorthPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600);
            otherSpaceship = SpaceshipFactory.createSpaceship(nextSpaceship.position.getX(), 600);
            newSpaceship.setDirection(spaceship);
            otherSpaceship.setDirection(nextSpaceship);
            addSpaceship(newSpaceship, otherSpaceship);
        } else if (checkEastPosition(spaceship) && checkEastPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY());
            otherSpaceship = SpaceshipFactory.createSpaceship(-76, nextSpaceship.position.getY());
            newSpaceship.setDirection(spaceship);
            otherSpaceship.setDirection(nextSpaceship);
            addSpaceship(newSpaceship, otherSpaceship);
        } else if (checkSouthPosition(spaceship) && checkSouthPosition(nextSpaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64);
            otherSpaceship = SpaceshipFactory.createSpaceship(nextSpaceship.position.getX(), -64);
            newSpaceship.setDirection(spaceship);
            otherSpaceship.setDirection(nextSpaceship);
            addSpaceship(newSpaceship, otherSpaceship);
        }
    }

    //@Author Tobias Engblom
    private void checkWrapAround(Spaceship spaceship) {
        if (checkWestPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(788, spaceship.position.getY());
            newSpaceship.setDirection(spaceship);
            addSpaceship(newSpaceship);
        } else if (checkNorthPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), 600);
            newSpaceship.setDirection(spaceship);
            addSpaceship(newSpaceship);
        } else if (checkEastPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(-76, spaceship.position.getY());
            newSpaceship.setDirection(spaceship);
            addSpaceship(newSpaceship);
        } else if (checkSouthPosition(spaceship)) {
            newSpaceship = SpaceshipFactory.createSpaceship(spaceship.position.getX(), -64);
            newSpaceship.setDirection(spaceship);
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

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public double getPlayingFieldWidth() {
        return playingFieldWidth;
    }

    public double getPlayingFieldHeight() {
        return playingFieldHeight;
    }

}