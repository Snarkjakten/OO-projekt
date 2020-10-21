package Model;

import Interfaces.ITimeObservable;
import Interfaces.ITimeObserver;
import Model.Entities.Player.Spaceship;
import Model.Entities.Projectiles.LaserBeam;
import Model.Movement.AbstractGameObject;
import Model.Movement.CollisionHandler;
import View.Sound.SoundHandler;

import java.util.ArrayList;
import java.util.List;

public class GameLoop implements ITimeObservable {
    private GameWorld gameWorld;
    private PausableAnimationTimer gameLoop;

    private final HighScoreHandler scoreHandler = new HighScoreHandler();
    private ScoreCalculator scoreCalculator;
    private WaveManager waveManager;

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    private CollisionHandler collisionHandler;

    private List<ITimeObserver> timeObservers;

    private List<AbstractGameObject> gameObjects;

    public GameLoop() {
        this.gameWorld = GameWorld.getInstance();
        scoreCalculator = new ScoreCalculator();
        waveManager = new WaveManager();
        collisionHandler = new CollisionHandler();
        gameObjects = gameWorld.getGameObjects();
        timeObservers = new ArrayList<>();


        gameLoop = new PausableAnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;

            final long animationNanoTime = System.nanoTime();

            @Override
            public void tick(long currentNanoTime) {
                checkGameWorld();

                /**
                 * Calculates time since last update
                 * @author Irja Vuorela
                 */
                currentNanoTime = System.nanoTime();
                double deltaTime = (currentNanoTime - previousNanoTime) / 1e9;
                double animationTime = (currentNanoTime - animationNanoTime) / 1e9;

                //notifyPlayingFieldObservers(gameWorld.getPlayingFieldWidth(), gameWorld.getPlayingFieldHeight());

                /**
                 * update positions and notify observers
                 * @author Irja vuorela
                 */
                for (AbstractGameObject gameObject : gameObjects) {
                    gameObject.move(deltaTime);
                   // notifyGameObjectObservers(gameObject.getHitBoxes(), gameObject.getClass(), gameObject.getWidth(), gameObject.getHeight());

                }

                collisionHandler.handleCollision(gameObjects);
                //notifySpaceshipObservers(gameWorld.getSpaceship());

                //End of collision handling -----------------------------------

                waveManager.projectileSpawner(calculateElapsedTime(getStartNanoTime()), gameObjects, deltaTime, 1, 25);

                gameWorld.wrapAround(gameWorld.getSpaceship());

                long elapsedTime = calculateElapsedTime(getStartNanoTime());
                notifyTimeObservers(elapsedTime, animationTime);

                endGame();
                previousNanoTime = currentNanoTime;

                // todo: use to check for bad frame rate
                if (deltaTime > 0.07) {
                    System.out.println("deltaTime: " + deltaTime);
                }
            }
        };
    }

    public void checkGameWorld() {
        this.gameWorld = GameWorld.getInstance();
    }

    private void endGame() { //TODO: Broken plz fix
        if (gameWorld.getSpaceship().getHp() <= 0) {
            gameWorld.setGameOver(true);
            //notifyGameOverObservers(gameWorld.getIsGameOver(), scoreCalculator.getPoints());
            gameObjects.clear();
            //collisionObservers.remove(gameWorld.getSpaceship());
            gameWorld.createNewGameWorld();
            gameWorld = GameWorld.getInstance();
            //notifyGameWorldObservers();
            gameLoop.stop();
            //collisionObservers.add(gameWorld.getSpaceship());
            scoreHandler.handleScore(scoreCalculator.getPoints());
        }
    }

    // Calculates elapsed time
    public long calculateElapsedTime(long startNanoTime) {
        long currentNanoTime = System.nanoTime();
        return currentNanoTime - startNanoTime;
    }

    @Override
    public void notifyTimeObservers(long time, double deltaTime) {
        for (ITimeObserver obs : timeObservers) {
            obs.actOnEvent(time, deltaTime);
        }
    }

    @Override
    public void addTimeObserver(ITimeObserver obs) {
        this.timeObservers.add(obs);
    }

    @Override
    public void removeObserver(ITimeObserver obs) {
        this.timeObservers.remove(obs);
    }
}
