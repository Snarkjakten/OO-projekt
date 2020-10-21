package Model;

import Model.Entities.HitBox;

import Model.Entities.Player.Spaceship;
import Model.Entities.Player.SpaceshipFactory;
import Model.Movement.AbstractGameObject;

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

    //@Author Tobias Engblom
    public void wrapAround(Spaceship spaceship) {
        HitBox hitBox = spaceship.getHitBoxes().get(0);
        int size = spaceship.getHitBoxes().size();
        if (size == 1) checkWrapAround(spaceship, hitBox);
        else if (size == 2) checkWrapAround(spaceship, hitBox, spaceship.getHitBoxes().get(1));
        checkInactive(spaceship.getHitBoxes());
    }

    //@Author Tobias Engblom
    private void addHitBoxesToSpaceship(Spaceship spaceship, HitBox hitBox1, HitBox hitBox2) {
        spaceship.getHitBoxes().add(hitBox1);
        spaceship.getHitBoxes().add(hitBox2);
    }

    //@Author Tobias Engblom
    private void checkWrapAround(Spaceship spaceship, HitBox hitBox1, HitBox hitBox2) {
        HitBox newHitBox1;
        HitBox newHitBox2;
        if (checkWestPosition(hitBox1) && checkWestPosition(hitBox2)) {
            newHitBox1 = new HitBox(788, hitBox1.getYPos(), spaceship.getWidth(), spaceship.getHeight());
            newHitBox2 = new HitBox(788, hitBox2.getYPos(), spaceship.getWidth(), spaceship.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        } else if (checkNorthPosition(hitBox1) && checkNorthPosition(hitBox2)) {
            newHitBox1 = new HitBox(hitBox1.getXPos(), 600, spaceship.getWidth(), spaceship.getHeight());
            newHitBox2 = new HitBox(hitBox2.getXPos(), 600, spaceship.getWidth(), spaceship.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        } else if (checkEastPosition(hitBox1) && checkEastPosition(hitBox2)) {
            newHitBox1 = new HitBox(-76, hitBox1.getYPos(), spaceship.getWidth(), spaceship.getHeight());
            newHitBox2 = new HitBox(-76, hitBox2.getYPos(), spaceship.getWidth(), spaceship.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        } else if (checkSouthPosition(hitBox1) && checkSouthPosition(hitBox2)) {
            newHitBox1 = new HitBox(hitBox1.getXPos(), -64, spaceship.getWidth(), spaceship.getHeight());
            newHitBox2 = new HitBox(hitBox2.getXPos(), -64, spaceship.getWidth(), spaceship.getHeight());
            addHitBoxesToSpaceship(spaceship, newHitBox1, newHitBox2);
        }
    }

    //@Author Tobias Engblom
    private void checkWrapAround(Spaceship spaceship, HitBox hitBox) {
        if (checkWestPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(788, hitBox.getYPos(), spaceship.getWidth(), spaceship.getHeight()));
        else if (checkNorthPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(hitBox.getXPos(), 600, spaceship.getWidth(), spaceship.getHeight()));
        else if (checkEastPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(-76, hitBox.getYPos(), spaceship.getWidth(), spaceship.getHeight()));
        else if (checkSouthPosition(hitBox))
            spaceship.getHitBoxes().add(new HitBox(hitBox.getXPos(), -64, spaceship.getWidth(), spaceship.getHeight()));
    }

    /**
     * Inactivates hitBox if it's outside the map
     *
     * @param hitBoxes The list of hitBoxes of the current spaceship
     * @author Tobias Engblom
     */
    private void checkInactive(List<HitBox> hitBoxes) {
        hitBoxes.removeIf(hitBox -> hitBox.getXPos() < -76 || hitBox.getXPos() > 788 || hitBox.getYPos() < -64 || hitBox.getYPos() > 600);
    }

    /**
     * Checks if active spaceship is beginning to go outside west wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkWestPosition(HitBox hitBox) {
        return hitBox.getXPos() <= -12;
    }

    /**
     * Checks if active spaceship is beginning to go outside north wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkNorthPosition(HitBox hitBox) {
        return hitBox.getYPos() <= 0;
    }

    /**
     * Checks if active spaceship is beginning to go outside east wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkEastPosition(HitBox hitBox) {
        return hitBox.getXPos() >= 724;
    }

    /**
     * Checks if active spaceship is beginning to go outside south wall
     *
     * @param hitBox one of the spaceship's hitBoxes
     * @return true if the hitBox is at the wall or outside
     * @author Tobias Engblom
     */
    private boolean checkSouthPosition(HitBox hitBox) {
        return hitBox.getYPos() >= 536;
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