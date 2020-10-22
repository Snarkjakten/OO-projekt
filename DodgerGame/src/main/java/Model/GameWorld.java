package Model;

import Model.Entities.HitBox;

import Model.Entities.Player.Spaceship;
import Model.Entities.Player.SpaceshipFactory;
import Model.Entities.AbstractGameObject;

import java.util.ArrayList;
import java.util.List;


public class GameWorld {
    private final List<AbstractGameObject> gameObjects;
    private Spaceship spaceship;
    private static GameWorld instance = null;
    private boolean isGameOver;

    private static final double playingFieldWidth = 800;
    private static final double playingFieldHeight = 600;

    private GameWorld() {
        this.isGameOver = false;
        this.gameObjects = new ArrayList<>();
        initSpaceship();
    }

    public void createNewGameWorld() {
        reset();
    }

    private void reset() {
        this.isGameOver = false;
        this.gameObjects.clear();
        initSpaceship();
    }

    /**
     * @author Tobias Engblom
     */
    private void initSpaceship() {
        this.spaceship = SpaceshipFactory.createSpaceship(368, 268, 32, 32);
        gameObjects.add(spaceship);
    }

    /**
     * @author Tobias Engblom
     */
    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    /**
     * @author Tobias Engblom
     */
    public Spaceship getSpaceship() {
        return this.spaceship;
    }

    /**
     * @author Tobias Engblom
     */
    public List<AbstractGameObject> getGameObjects() {
        return this.gameObjects;
    }

    /**
     * @param spaceship the spaceship we want to wrap around
     * @author Tobias Engblom
     */
    public void wrapAround(Spaceship spaceship) {
        HitBox hitBox = spaceship.getHitBoxes().get(0);
        int size = spaceship.getHitBoxes().size();
        if (size == 1) {
            checkWrapAround(spaceship, hitBox);
        } else if (size == 2) {
            checkWrapAround(spaceship, hitBox, spaceship.getHitBoxes().get(1));
            checkInactive(spaceship.getHitBoxes());
        }
    }

    /**
     * @param spaceship the spaceship we want to add the hitBoxes to
     * @param hitBox1   the first hitBox
     * @param hitBox2   the second hitBox
     * @author Tobias Engblom
     */
    private void addHitBoxesToSpaceship(Spaceship spaceship, HitBox hitBox1, HitBox hitBox2) {
        spaceship.getHitBoxes().add(hitBox1);
        spaceship.getHitBoxes().add(hitBox2);
    }

    /**
     * @param spaceship the spaceship we want to wrap around
     * @param hitBox1   the first hitBox
     * @param hitBox2   the second hitBox
     * @author Tobias Engblom
     */
    private void checkWrapAround(Spaceship spaceship, HitBox hitBox1, HitBox hitBox2) {
        HitBox newHitBox1;
        HitBox newHitBox2;
        if (checkWestPosition(hitBox1) && checkWestPosition(hitBox2)) {
            newHitBox1 = new HitBox(getPlayingFieldWidth() - 12, hitBox1.getY(), hitBox1.getWidth(), hitBox1.getHeight());
            newHitBox2 = new HitBox(getPlayingFieldWidth() - 12, hitBox2.getY(), hitBox2.getWidth(), hitBox2.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        } else if (checkNorthPosition(hitBox1) && checkNorthPosition(hitBox2)) {
            newHitBox1 = new HitBox(hitBox1.getX(), getPlayingFieldHeight(), hitBox1.getWidth(), hitBox1.getHeight());
            newHitBox2 = new HitBox(hitBox2.getX(), getPlayingFieldHeight(), hitBox2.getWidth(), hitBox2.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        } else if (checkEastPosition(hitBox1) && checkEastPosition(hitBox2)) {
            newHitBox1 = new HitBox(-hitBox1.getWidth() - 12, hitBox1.getY(), hitBox1.getWidth(), hitBox1.getHeight());
            newHitBox2 = new HitBox(-hitBox2.getWidth() - 12, hitBox2.getY(), hitBox2.getWidth(), hitBox2.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        } else if (checkSouthPosition(hitBox1) && checkSouthPosition(hitBox2)) {
            newHitBox1 = new HitBox(hitBox1.getX(), -hitBox1.getHeight(), hitBox1.getWidth(), hitBox1.getHeight());
            newHitBox2 = new HitBox(hitBox2.getX(), -hitBox2.getHeight(), hitBox2.getWidth(), hitBox2.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        }
    }

    /**
     * @param spaceship the spaceship we want to wrap around
     * @param hitBox    the current hitBox
     * @author Tobias Engblom
     */
    private void checkWrapAround(Spaceship spaceship, HitBox hitBox) {
        if (checkWestPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(getPlayingFieldWidth() - 12, hitBox.getY(), hitBox.getWidth(), hitBox.getHeight()));
        else if (checkNorthPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(hitBox.getX(), getPlayingFieldHeight(), hitBox.getWidth(), hitBox.getHeight()));
        else if (checkEastPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(-hitBox.getWidth() - 12, hitBox.getY(), hitBox.getWidth(), hitBox.getHeight()));
        else if (checkSouthPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(hitBox.getX(), -hitBox.getHeight(), hitBox.getWidth(), hitBox.getHeight()));
    }

    /**
     * Inactivates hitBox if it's outside the map
     *
     * @param hitBoxes The list of hitBoxes of the current spaceship
     * @author Tobias Engblom
     */
    private void checkInactive(List<HitBox> hitBoxes) {
        hitBoxes.removeIf(hitBox -> hitBox.getX() < -hitBox.getWidth() - 12 || hitBox.getX() > getPlayingFieldWidth() - 12 || hitBox.getY() < -hitBox.getHeight() || hitBox.getY() > getPlayingFieldHeight());
    }

    /**
     * Checks if active spaceship is beginning to go outside west wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkWestPosition(HitBox hitBox) {
        return hitBox.getX() <= -12;
    }

    /**
     * Checks if active spaceship is beginning to go outside north wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkNorthPosition(HitBox hitBox) {
        return hitBox.getY() <= 0;
    }

    /**
     * Checks if active spaceship is beginning to go outside east wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkEastPosition(HitBox hitBox) {
        return hitBox.getX() >= getPlayingFieldWidth() - hitBox.getWidth() - 12;
    }

    /**
     * Checks if active spaceship is beginning to go outside south wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkSouthPosition(HitBox hitBox) {
        return hitBox.getY() >= getPlayingFieldHeight() - hitBox.getHeight();
    }

    /**
     * @return true if game over
     */
    public boolean getIsGameOver() {
        return isGameOver;
    }

    /**
     * Setter for game over
     *
     * @param gameOver true if game over
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /**
     * @return the width of the playing field
     */
    public static double getPlayingFieldWidth() {
        return playingFieldWidth;
    }

    /**
     * @return the height of the playing field
     */
    public static double getPlayingFieldHeight() {
        return playingFieldHeight;
    }

}