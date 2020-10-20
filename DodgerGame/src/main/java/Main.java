import Interfaces.*;
import Model.Entities.Player.Player;
import Model.Entities.Player.Spaceship;
import Model.GameWorld;
import Model.HighScoreHandler;
import Model.Movement.AbstractGameObject;
import Model.Movement.CollisionHandler;
import Model.ScoreCalculator;
import Model.WaveManager;
import View.*;
import View.Sound.GameObjectsSounds;
import View.Sound.SoundHandler;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements ICollisionObservable, IGameObjectObservable, IGameOverObservable, IPlayerObservable, IPlayingFieldObservable, ISoundObservable, ITimeObservable {

    private GameWorld gameWorld;
    private AnimationTimer gameLoop;

    private List<AbstractGameObject> gameObjects;
    private List<IGameObjectObserver> gameObjectObservers;
    private List<ITimeObserver> timeObservers;
    private List<ISoundObserve> soundObservers;
    private List<IGameOverObserver> gameOverObservers;
    private List<IPlayingFieldObserver> playingFieldObservers;
    private List<IPlayerObserver> playerObservers;
    private List<ICollisionObserver> collisionObservers;
    private List<AbstractGameObject> projectileWave;

    private SoundHandler soundHandler = new SoundHandler();
    private HighScoreHandler scoreHandler = new HighScoreHandler();
    private ScoreCalculator scoreCalculator;

    private GraphicsContext graphicsContext;

    private long startNanoTime;

    @Override
    public void start(Stage stage) throws Exception {
        gameWorld = GameWorld.getInstance();
        scoreCalculator = new ScoreCalculator();
        gameObjects = gameWorld.getGameObjects();
        Window window = new Window(stage, gameWorld.getPlayingFieldWidth(), gameWorld.getPlayingFieldHeight());
        graphicsContext = window.getGraphicsContext();
        MainMenu mainMenu = new MainMenu();
        HighScoreMenu highScoreMenu = new HighScoreMenu();
        CharacterMenu characterMenu = new CharacterMenu();
        GameOverMenu gameOverMenu = new GameOverMenu();
        WaveManager waveManager = new WaveManager();

        GameObjectGUI gameObjectGUI = new GameObjectGUI(graphicsContext);
        HealthBarGUI healthBarGUI = new HealthBarGUI(graphicsContext);
        ShieldGUI shieldGUI = new ShieldGUI(graphicsContext);
        //LaserGUI laserGUI = new LaserGUI(graphicsContext, 10, true);
        BackgroundView backgroundView = new BackgroundView(graphicsContext);
        ITimeObserver timeView = new TimeView(graphicsContext);
        startNanoTime = System.nanoTime(); //TODO Fix time bug

        gameLoop = new AnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;
            int updateCounter = 60;

            long animationNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                checkGameWorld();

                /**
                 * Calculates time since last update
                 * @author Irja Vuorela
                 */
                currentNanoTime = System.nanoTime();
                double deltaTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                double animationTime = (currentNanoTime - animationNanoTime) / 1000000000.0;

                notifyPlayingFieldObservers(gameWorld.getPlayingFieldWidth(), gameWorld.getPlayingFieldHeight());

                /**
                 * update positions and notify observers
                 * @author Irja vuorela
                 */
                for (AbstractGameObject gameObject : gameObjects) {
                    gameObject.move(deltaTime);
                    notifyGameObjectObservers(gameObject.position.getX(), gameObject.position.getY(), gameObject.getClass(), gameObject.getHeight(), gameObject.getWidth());
                }

                List<AbstractGameObject> toBeRemoved;
                toBeRemoved = new ArrayList<>();

                CollisionHandler collisionHandler = new CollisionHandler();

                for (AbstractGameObject gameObject : gameObjects) {
                    notifyGameObjectObservers(gameObject.position.getX(), gameObject.position.getY(), gameObject.getClass(), gameObject.getHeight(), gameObject.getWidth());
                    for (AbstractGameObject a : gameObjects) {
                        if (collisionHandler.checkCollision(gameObject, a) && !gameObject.getCollided() && !a.getCollided()) {
                            // TODO Fråga handledaren om kopia av objekt
                            if (a instanceof Spaceship) {
                                notifySoundObservers(gameObject.getClass());
                                notifyCollisionObservers(gameObject);
                                toBeRemoved.add(gameObject);
                            } else if (gameObject instanceof Spaceship) {
                                notifySoundObservers(a.getClass());
                                notifyCollisionObservers(a);
                                toBeRemoved.add(a);
                            }
                            collisionHandler.collide(a, gameObject);
                        }
                    }
                }
                notifyPlayerObservers(gameWorld.getPlayer());

                for (AbstractGameObject a : toBeRemoved) {
                    gameObjects.remove(a);
                }
                //End of collision handling -----------------------------------

                waveManager.projectileSpawner(calculateElapsedTime(startNanoTime), gameObjects,deltaTime, 1, 25);

                gameWorld.wrapAround();

                long elapsedTime = calculateElapsedTime(startNanoTime);
                notifyTimeObservers(elapsedTime, animationTime);


                endGame();
                previousNanoTime = currentNanoTime;

                /* todo: use to check for bad frame rate
                if(deltaTime > 0.07) {
                    System.out.println("deltaTime: " + deltaTime);
                }
                */
            }
        };
        ViewController vc = new ViewController(window, mainMenu, highScoreMenu, characterMenu, gameOverMenu, stage, gameLoop, gameObjectGUI);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();
        window.init();
        //@Author tobbe
        gameOverObservers = new ArrayList<>();
        gameObjectObservers = new ArrayList<>();
        playingFieldObservers = new ArrayList<>();
        collisionObservers = new ArrayList<>();
        timeObservers = new ArrayList<>();
        soundObservers = new ArrayList<>();
        playerObservers = new ArrayList<>();

        //addObserver(laserGUI);
        addObserver(gameObjectGUI);
        addObserver(vc);
        addTimeObserver(timeView);
        addTimeObserver(shieldGUI);
        addTimeObserver(scoreCalculator);
        addPlayerObserver(shieldGUI);
        addPlayerObserver(healthBarGUI);
        addCollisionObserver(gameWorld.getPlayer());
        addObserver(soundHandler);
        addObserver(backgroundView);

        soundHandler.musicPlayer(GameObjectsSounds.getBackgroundMusicPath());

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void checkGameWorld() {
        this.gameWorld = GameWorld.getInstance();
    }

    /**
     * @author Irja Vuorela
     */
    @Override
    public void addObserver(IGameObjectObserver obs) {
        gameObjectObservers.add(obs);
    }

    /**
     * @author Irja Vuorela
     */
    @Override
    public void removeObserver(IGameObjectObserver obs) {
        gameObjectObservers.remove(obs);
    }

    @Override
    public void notifyGameObjectObservers(double x, double y, Class c, double height, double width) {
        for (IGameObjectObserver obs : gameObjectObservers) {
            obs.actOnEvent(x, y, c, height, width);
        }
    }

    @Override
    public void removeObserver(ISoundObserve obs) {
        this.soundObservers.remove(obs);
    }

    @Override
    public void addObserver(ISoundObserve obs) {
        this.soundObservers.add(obs);
    }

    @Override
    public void notifySoundObservers(Class c) {
        for (ISoundObserve obs : soundObservers) {
            obs.actOnEvent(c);
        }
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

    private void endGame() {
        if (gameWorld.getPlayer().getHp() <= 0) {
            gameWorld.setGameOver(true);
            notifyGameOverObservers(gameWorld.getIsGameOver(), scoreCalculator.getPoints());
            gameObjects.clear();
            collisionObservers.remove(gameWorld.getPlayer());
            gameWorld.createNewGameWorld();
            gameWorld = GameWorld.getInstance();
            gameLoop.stop();
            collisionObservers.add(gameWorld.getPlayer());
            scoreHandler.handleScore(scoreCalculator.getPoints());
        }
    }

    @Override
    public void notifyPlayingFieldObservers(double width, double height) {
        for (IPlayingFieldObserver obs : playingFieldObservers) {
            obs.actOnEvent(width, height);
        }
    }

    @Override
    public void addObserver(IPlayingFieldObserver obs) {
        this.playingFieldObservers.add(obs);
    }

    @Override
    public void removeObserver(IPlayingFieldObserver obs) {
        this.playingFieldObservers.remove(obs);
    }

    // Calculates elapsed time
    public long calculateElapsedTime(long startNanoTime) {
        long currentNanoTime = System.nanoTime();
        return currentNanoTime - startNanoTime;
    }

    @Override
    public void notifyGameOverObservers(boolean isGameOver, int points) {
        for (IGameOverObserver obs : gameOverObservers) {
            obs.actOnEvent(isGameOver, points);
        }
    }

    @Override
    public void addObserver(IGameOverObserver obs) {
        this.gameOverObservers.add(obs);
    }

    @Override
    public void removeObserver(IGameOverObserver obs) {
        this.gameOverObservers.remove(obs);
    }

    @Override
    public void addPlayerObserver(IPlayerObserver obs) {
        playerObservers.add(obs);
    }

    @Override
    public void removePlayerObserver(IPlayerObserver obs) {
        playerObservers.remove(obs);
    }

    @Override
    public void notifyPlayerObservers(Player player) {
        Player copyOfPlayer = new Player(player.getSpaceships(), player.getNrOfShields(), player.getHp());
        for (IPlayerObserver obs : playerObservers) {
            obs.actOnEvent(copyOfPlayer);
        }
    }

    /*
    private Player copyPlayer(Player object) {
        Player copyOfPlayer = new Player();
        copyOfPlayer.setHp(object.getHp());
        copyOfPlayer.setPoints(object.getPoints());
        copyOfPlayer.setNrOfShields(object.getNrOfShields());
        copyOfPlayer.setSpaceships(object.getSpaceships());
        return copyOfPlayer;
    }

     */

    @Override
    public void notifyCollisionObservers(AbstractGameObject gameObject) {
        for (ICollisionObserver obs : collisionObservers) {
            obs.actOnCollisionEvent(gameObject);
        }
    }

    @Override
    public void addCollisionObserver(ICollisionObserver obs) {
        this.collisionObservers.add(obs);
    }

    @Override
    public void removeCollisionObserver(ICollisionObserver obs) {
        this.collisionObservers.remove(obs);
    }
}
