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

    private GameWorld gameWorld;
    private PausableAnimationTimer gameLoop;
    private HighScoreHandler scoreHandler;
    private ScoreCalculator scoreCalculator;
    private WaveManager waveManager;
    private CollisionHandler collisionHandler;
    private List<AbstractGameObject> gameObjects;
    private List<ITimeObserver> timeObservers;
    private List<IGameOverObserver> gameOverObservers;

    /**
     * @authors Everyone
     */
    public GameLoop() {
        this.gameWorld = GameWorld.getInstance();
        scoreCalculator = new ScoreCalculator();
        scoreHandler = new HighScoreHandler();
        waveManager = new WaveManager();
        collisionHandler = new CollisionHandler();
        gameObjects = gameWorld.getGameObjects();
        timeObservers = new ArrayList<>(); //todo: ska dessa ligga utanför? vad är bäst?
        gameOverObservers = new ArrayList<>();

        gameLoop = new PausableAnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;

            @Override
            public void tick(long currentNanoTime) {
                setGameWorld(); //todo why in every tick?

                /**
                 * Calculates time since last update
                 *  @author Irja Vuorela
                 */
                currentNanoTime = System.nanoTime();
                double deltaTime = (currentNanoTime - previousNanoTime) / 1e9;

                long elapsedTime = calculateElapsedTime(getStartNanoTime());

                update(gameObjects, deltaTime, elapsedTime);
                notifyTimeObservers(elapsedTime, deltaTime);

                endGame();

                previousNanoTime = currentNanoTime;
            }
        };
    }

    public void setGameWorld() {
        this.gameWorld = GameWorld.getInstance();
    }

    /**
     * @authors Everyone
     */
    private void endGame() {
        if (gameWorld.getSpaceship().getHp() <= 0) {
            gameWorld.setGameOver(true);
            notifyGameOverObservers(gameWorld.getIsGameOver(), scoreCalculator.getPoints());
            gameObjects.clear();
            gameWorld.createNewGameWorld();
            gameWorld = GameWorld.getInstance();
            gameLoop.stop();
            scoreHandler.handleScore(scoreCalculator.getPoints());
        }
    }

    /**
     * Calculates elapsed time
     *
     * @param startNanoTime time at the start of the simulation
     * @return elapsed time since start of the simulation
     * @autor Isak Almeros
     */
    public long calculateElapsedTime(long startNanoTime) {
        long currentNanoTime = System.nanoTime();
        return currentNanoTime - startNanoTime;
    }

    @Override
    public void notifyTimeObservers(long time, double deltaTime) {
        for (ITimeObserver obs : timeObservers) {
            obs.actOnTimeEvent(time, deltaTime);
        }
    }

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

    @Override
    public void addGameOverObserver(IGameOverObserver obs) {
        gameOverObservers.add(obs);
    }

    @Override
    public void removeGameOverObserver(IGameOverObserver obs) {
        gameOverObservers.remove(obs);
    }

    private void update(List<AbstractGameObject> gameObjects, double deltaTime, long elapsedTime) {
        /**
         * update positions
         * @author Irja vuorela
         */
        for (AbstractGameObject gameObject : gameObjects) {
            gameObject.move(deltaTime);
        }

        gameWorld.wrapAround(gameWorld.getSpaceship());
        collisionHandler.handleCollision(gameObjects);
        waveManager.projectileSpawner(elapsedTime, gameObjects, deltaTime, 1, 30);
        scoreCalculator.calculateScore(elapsedTime);
    }

    //todo: borde inte ligga i gameloop.java
    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }
}
