import Controller.KeyController;
import Controller.ViewController;
import Model.Entities.HitBox;
import Interfaces.*;
import Model.Entities.Player.Spaceship;
import Model.Entities.Projectiles.LaserBeam;
import Model.GameWorld;
import Model.HighScoreHandler;
import Model.Movement.AbstractGameObject;
import Model.Movement.CollisionHandler;
import Model.PausableAnimationTimer;
import Model.ScoreCalculator;
import Model.WaveManager;
import View.*;
import View.Sound.GameObjectsSounds;
import View.Sound.SoundHandler;
import View.Window;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements ICollisionObservable, IGameObjectObservable, IGameOverObservable, ISpaceshipObservable, IPlayingFieldObservable, ISoundObservable, ITimeObservable, IGameWorldObservable {

    private GameWorld gameWorld;
    private PausableAnimationTimer gameLoop;

    private List<AbstractGameObject> gameObjects;
    private List<IGameObjectObserver> gameObjectObservers;
    private List<ITimeObserver> timeObservers;
    private List<ISoundObserve> soundObservers;
    private List<IGameOverObserver> gameOverObservers;
    private List<IPlayingFieldObserver> playingFieldObservers;
    private List<ISpaceshipObserver> spaceshipObservers;
    private List<ICollisionObserver> collisionObservers;
    private List<IGameWorldObserver> gameWorldObservers;
    private List<AbstractGameObject> projectileWave;

    private final SoundHandler soundHandler = new SoundHandler();
    private final HighScoreHandler scoreHandler = new HighScoreHandler();
    private ScoreCalculator scoreCalculator;

    @Override
    public void start(Stage stage) throws Exception {
        gameWorld = GameWorld.getInstance();
        scoreCalculator = new ScoreCalculator();
        gameObjects = gameWorld.getGameObjects();
        Window window = new Window(gameWorld.getPlayingFieldWidth(), gameWorld.getPlayingFieldHeight());
        GraphicsContext graphicsContext = window.getGraphicsContext();
        AbstractMenu mainMenu = MenuFactory.createMainMenu();
        AbstractMenu highScoreMenu = MenuFactory.createHighScoreMenu();
        AbstractMenu characterMenu = MenuFactory.createCharacterMenu();
        AbstractMenu gameOverMenu = MenuFactory.createGameOverMenu();
        AbstractMenu pauseMenu = MenuFactory.createPauseMenu();
        WaveManager waveManager = new WaveManager();

        CollisionHandler collisionHandler = new CollisionHandler();

        LaserGUI laserGUI = LaserGUI.getInstance();
        GameObjectGUI gameObjectGUI = new GameObjectGUI(graphicsContext);
        HealthBarGUI healthBarGUI = new HealthBarGUI(graphicsContext);
        ShieldGUI shieldGUI = new ShieldGUI(graphicsContext);
        //LaserGUI laserGUI = new LaserGUI(graphicsContext, 10, true);
        BackgroundView backgroundView = new BackgroundView(graphicsContext);
        ITimeObserver timeView = new TimeView(graphicsContext);

        gameLoop = new PausableAnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;
            final long animationNanoTime = System.nanoTime();

            @Override
            public void tick(long currentNanoTime) {
                /**
                 * Calculates time since last update
                 * @author Irja Vuorela
                 */
                currentNanoTime = System.nanoTime();
                double deltaTime = (currentNanoTime - previousNanoTime) / 1e9;
                double animationTime = (currentNanoTime - animationNanoTime) / 1e9;

                notifyPlayingFieldObservers(gameWorld.getPlayingFieldWidth(), gameWorld.getPlayingFieldHeight());

                /**
                 * update positions and notify observers
                 * @author Irja vuorela
                 */
                for (AbstractGameObject gameObject : gameObjects) {
                    gameObject.move(deltaTime);
                    notifyGameObjectObservers(gameObject.getHitBoxes(), gameObject.getClass(), gameObject.getWidth(), gameObject.getHeight());
                }

                List<AbstractGameObject> toBeRemoved;
                toBeRemoved = new ArrayList<>();



                for (AbstractGameObject gameObject : gameObjects) {
                    notifyGameObjectObservers(gameObject.getHitBoxes(), gameObject.getClass(), gameObject.getWidth(), gameObject.getHeight());
                    for (AbstractGameObject a : gameObjects) {
                        if (collisionHandler.checkCollision(gameObject, a) && !gameObject.getCollided() && !a.getCollided()) {
                            if (a instanceof Spaceship) {
                                notifySoundObservers(gameObject.getClass());
                                notifyCollisionObservers(gameObject);
                                if (!(gameObject instanceof LaserBeam)) {
                                    toBeRemoved.add(gameObject);
                                }
                            } else if (gameObject instanceof Spaceship) {
                                notifySoundObservers(a.getClass());
                                notifyCollisionObservers(a);
                                if (!(a instanceof LaserBeam)) {
                                    toBeRemoved.add(a);
                                }
                            }
                            collisionHandler.collide(a, gameObject);
                        }
                    }
                }
                notifySpaceshipObservers(gameWorld.getSpaceship());

                for (AbstractGameObject a : toBeRemoved) {
                    gameObjects.remove(a);
                }
                //End of collision handling -----------------------------------

                waveManager.projectileSpawner(calculateElapsedTime(getStartNanoTime()), gameObjects, deltaTime, 1, 25);

                gameWorld.wrapAround(gameWorld.getSpaceship());

                long elapsedTime = calculateElapsedTime(getStartNanoTime());
                notifyTimeObservers(elapsedTime, animationTime);


                endGame();
                previousNanoTime = currentNanoTime;

                /*
                // todo: use to check for bad frame rate
                if (deltaTime > 0.07) {
                    System.out.println("deltaTime: " + deltaTime);
                }

                 */
            }
        };
        ViewController vc = new ViewController(window, mainMenu, highScoreMenu, characterMenu, gameOverMenu, stage, gameLoop, gameObjectGUI, pauseMenu);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

        /**
         * Handle key pressed
         * @author Irja Vuorela
         */
        KeyController keyController = new KeyController(stage, gameLoop, pauseMenu);
        stage.getScene().setOnKeyPressed(
                keyController::handleKeyPressed);

        /**
         * Handle key released
         * @author Irja Vuorela
         */
        stage.getScene().setOnKeyReleased(
                keyController::handleKeyReleased
        );

        window.init();
        //@Author tobbe
        gameOverObservers = new ArrayList<>();
        gameObjectObservers = new ArrayList<>();
        playingFieldObservers = new ArrayList<>();
        collisionObservers = new ArrayList<>();
        timeObservers = new ArrayList<>();
        soundObservers = new ArrayList<>();
        spaceshipObservers = new ArrayList<>();
        gameWorldObservers = new ArrayList<>();

        addObserver(gameObjectGUI);
        addObserver(vc);
        addTimeObserver(timeView);
        addTimeObserver(laserGUI);
        addTimeObserver(shieldGUI);
        addSpaceshipObserver(shieldGUI);
        addSpaceshipObserver(healthBarGUI);
        addCollisionObserver(gameWorld.getSpaceship());
        addTimeObserver(scoreCalculator);
        addObserver(soundHandler);
        addObserver(backgroundView);
        addGameWorldObserver(keyController);

        soundHandler.musicPlayer(GameObjectsSounds.getBackgroundMusicPath());
    }

    public static void main(String[] args) {
        launch(args);
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
    public void notifyGameObjectObservers(List<HitBox> hitBoxes, Class c, double width, double height) {
        for (IGameObjectObserver obs : gameObjectObservers) {
            obs.actOnEvent(hitBoxes, c, width, height);
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

    private void endGame() { //TODO: Broken plz fix
        if (gameWorld.getSpaceship().getHp() <= 0) {
            gameWorld.setGameOver(true);
            notifyGameOverObservers(gameWorld.getIsGameOver(), scoreCalculator.getPoints());
            gameObjects.clear();
            collisionObservers.remove(gameWorld.getSpaceship());
            gameWorld.createNewGameWorld();
            gameWorld = GameWorld.getInstance();
            notifyGameWorldObservers();
            gameLoop.stop();
            collisionObservers.add(gameWorld.getSpaceship());
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
    public void addSpaceshipObserver(ISpaceshipObserver obs) {
        spaceshipObservers.add(obs);
    }

    @Override
    public void removeSpaceshipObserver(ISpaceshipObserver obs) {
        spaceshipObservers.remove(obs);
    }

    public void notifySpaceshipObservers(Spaceship spaceship) {
        for (ISpaceshipObserver obs : spaceshipObservers)
            obs.actOnEvent(spaceship);
    }

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

    @Override
    public void notifyGameWorldObservers() {
        for (IGameWorldObserver obs : gameWorldObservers)
            obs.actOnEvent();
    }

    @Override
    public void addGameWorldObserver(IGameWorldObserver obs) {
        this.gameWorldObservers.add(obs);
    }

    @Override
    public void removeGameWorldObserver(IGameWorldObserver obs) {
        this.gameWorldObservers.add(obs);
    }
}
