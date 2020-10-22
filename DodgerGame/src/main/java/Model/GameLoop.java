package Model;

import Interfaces.IGameOverObservable;
import Interfaces.IGameOverObserver;
import Interfaces.ITimeObservable;
import Interfaces.ITimeObserver;
import Model.Movement.AbstractGameObject;
import Model.Movement.CollisionHandler;

import java.util.ArrayList;
import java.util.List;

public class GameLoop implements ITimeObservable, IGameOverObservable {

    private final PausableAnimationTimer gameLoop;
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
    public GameLoop() {
        scoreCalculator = new ScoreCalculator();
        scoreHandler = new HighScoreHandler();
        waveManager = new WaveManager();
        collisionHandler = new CollisionHandler();
        gameObjects = GameWorld.getInstance().getGameObjects();
        timeObservers = new ArrayList<>(); //todo: ska dessa ligga utanför? vad är bäst?
        gameOverObservers = new ArrayList<>();

        gameLoop = new PausableAnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;

            @Override
            public void tick(long currentNanoTime) {
                /**
                 * Calculates time since last update
                 *  @author Irja Vuorela
                 */
                currentNanoTime = System.nanoTime(); //todo: varför går ej att skicka med currentNanoTime till metod för att bryta ut?
                double deltaTime = (currentNanoTime - previousNanoTime) / 1e9;

                long elapsedTime = calculateElapsedTime(getStartNanoTime());

                update(gameObjects, deltaTime, elapsedTime);
                notifyTimeObservers(elapsedTime, deltaTime);

                endGame();

                previousNanoTime = currentNanoTime;
            }
        };
    }

    /**
     * Ends the game when the player's health reaches 0.
     *
     * @authors Everyone
     */
    private void endGame() {
        if (GameWorld.getInstance().getSpaceship().getHp() <= 0) {
            GameWorld.getInstance().setGameOver(true);
            notifyGameOverObservers(GameWorld.getInstance().getIsGameOver(), scoreCalculator.getPoints());
            gameObjects.clear();
            GameWorld.getInstance().createNewGameWorld();
            gameLoop.stop();
            scoreHandler.handleScore(scoreCalculator.getPoints());
        }
    }

    /**
     * Update the game state
     *
     * @param gameObjects the list of game objects on the playing field
     * @param deltaTime   the length of last frame in the game loop
     * @param elapsedTime the elapsed time since the start of the simulation
     * @authors Irja, Isak, Viktor
     */
    private void update(List<AbstractGameObject> gameObjects, double deltaTime, long elapsedTime) {
        moveGameObjects(gameObjects, deltaTime);
        GameWorld.getInstance().wrapAround(GameWorld.getInstance().getSpaceship());
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

    /**
     * Calculates elapsed time.
     *
     * @param startNanoTime time at the start of the simulation
     * @return elapsed time since start of the simulation
     * @author Isak Almeros
     */
    public long calculateElapsedTime(long startNanoTime) {
        long currentNanoTime = System.nanoTime();
        return currentNanoTime - startNanoTime;
    }

    // Add, remove and notify observers --------------------------

    /**
     * @param time      the elapsed time since the start of the simulation
     * @param deltaTime the length of the last frame in the game loop
     */
    @Override
    public void notifyTimeObservers(long time, double deltaTime) {
        for (ITimeObserver obs : timeObservers) {
            obs.actOnTimeEvent(time, deltaTime);
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

    public PausableAnimationTimer getGameLoop() {
        return gameLoop;
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

    //todo: borde inte ligga i gameloop.java
    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }
}
