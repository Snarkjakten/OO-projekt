package Model;

import Interfaces.IGameOverObservable;
import Interfaces.IGameOverObserver;
import Interfaces.ITimeObservable;
import Interfaces.ITimeObserver;
import Model.Entities.AbstractGameObject;
import Model.Entities.Player.Spaceship;

import java.util.ArrayList;
import java.util.List;

public class Game implements ITimeObservable, IGameOverObservable {

    private final HighScoreHandler scoreHandler;
    private final ScoreCalculator scoreCalculator;
    private final WaveManager waveManager;
    private final CollisionHandler collisionHandler;
    private final List<AbstractGameObject> gameObjects;
    private final List<ITimeObserver> timeObservers;
    private final List<IGameOverObserver> gameOverObservers;

    /**
     * @authors Everyone
     */
    public Game() {
        scoreCalculator = new ScoreCalculator();
        scoreHandler = new HighScoreHandler();
        waveManager = new WaveManager();
        collisionHandler = new CollisionHandler();
        gameObjects = GameWorld.getInstance().getGameObjects();
        timeObservers = new ArrayList<>();
        gameOverObservers = new ArrayList<>();
    }

    /**
     * Update the game and all objects depending on delta time.
     *
     * @param deltaTime   length since last update
     * @param elapsedTime total time since the start
     * @author Olle Westerlund
     */
    public void updateWorld(Spaceship spaceship, double deltaTime, long elapsedTime) {
        update(gameObjects, spaceship, deltaTime, elapsedTime);
        notifyTimeObservers(elapsedTime);

        if (isGameOver(spaceship)) {
            gameOver();
        }
    }

    /**
     * Ends the game when the player's health reaches 0.
     *
     * @authors Everyone
     */
    private void gameOver() {
        GameWorld.getInstance().setGameOver(true);
        notifyGameOverObservers(GameWorld.getInstance().getIsGameOver(), scoreCalculator.getPoints());
        gameObjects.clear();
        scoreHandler.handleScore(scoreCalculator.getPoints());
    }

    /**
     * Check if the game is over
     * @return true if the game is over
     */
    public boolean isGameOver(Spaceship spaceship) {
        return (spaceship.getHp() <= 0);
    }

    /**
     * Creating a new gameWorld
     */
    public void startGame() {
        GameWorld.getInstance().createNewGameWorld();
    }

    /**
     * Update the game state
     *
     * @param gameObjects the list of game objects on the playing field
     * @param deltaTime   the length of last frame in the game loop
     * @param elapsedTime the elapsed time since the start of the simulation
     * @authors Irja, Isak, Viktor
     */
    private void update(List<AbstractGameObject> gameObjects, Spaceship spaceship, double deltaTime, long elapsedTime) {
        moveGameObjects(gameObjects, deltaTime);
        GameWorld.getInstance().wrapAround(spaceship);
        collisionHandler.handleCollision(gameObjects);
        waveManager.projectileSpawner(elapsedTime, gameObjects, deltaTime, 1, 30);
        scoreCalculator.calculateScore(elapsedTime);
    }

    /**
     * Update positions of all game objects.
     *
     * @param gameObjects a list of game objects on the playing field
     * @param deltaTime   the length of the last frame in the game loop
     * @author Irja vuorela
     */
    private void moveGameObjects(List<AbstractGameObject> gameObjects, double deltaTime) {
        for (AbstractGameObject gameObject : gameObjects) {
            gameObject.move(deltaTime);
        }
    }

    // Add, remove and notify observers --------------------------

    /**
     * @param time the elapsed time since the start of the simulation
     */
    @Override
    public void notifyTimeObservers(long time) {
        for (ITimeObserver obs : timeObservers) {
            obs.actOnTimeEvent(time);
        }
    }

    /**
     * @param obs an observer to be added to the list of observers
     */
    @Override
    public void addTimeObserver(ITimeObserver obs) {
        this.timeObservers.add(obs);
    }

    @Override
    public void removeTimeObserver(ITimeObserver obs) {
        this.timeObservers.remove(obs);
    }

    @Override
    public void notifyGameOverObservers(boolean isGameOver, int points) {
        for (IGameOverObserver obs : gameOverObservers) {
            obs.actOnGameOverEvent(isGameOver, points);
        }
    }

    /**
     * @param obs an observer to be added to the list of observers
     */
    @Override
    public void addGameOverObserver(IGameOverObserver obs) {
        gameOverObservers.add(obs);
    }

    /**
     * @param obs an observer to the added to the list of observers
     */
    @Override
    public void removeGameOverObserver(IGameOverObserver obs) {
        gameOverObservers.remove(obs);
    }

    // Getters and setters ---------------------------

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }
}
