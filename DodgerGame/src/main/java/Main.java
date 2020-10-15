import Game.Entities.Player.Player;
import Game.Entities.Player.Spaceship;
import Game.Entities.Projectiles.Projectile;
import Game.Entities.Projectiles.ProjectileFactory;
import Game.HighScoreHandler;
import Game.Movement.AbstractGameObject;
import Game.Movement.CollisionHandler;
import Interfaces.*;
import View.*;
import View.Sound.GameObjectsSounds;
import View.Sound.SoundHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements ICollisionObservable, IGameObjectObservable, IGameOverObservable, IPlayerObservable, IPlayingFieldObservable, ISoundObservable, ITimeObservable {

    private GameWorld gameWorld;
    private PausableAnimationTimer gameLoop;

    private List<AbstractGameObject> gameObjects;
    private List<IGameObjectObserver> gameObjectObservers;
    private List<ITimeObserver> timeObservers;
    private List<ISoundObserve> soundObservers;
    private List<IGameOverObserver> gameOverObservers;
    private List<IPlayingFieldObserver> playingFieldObservers;
    private List<IPlayerObserver> playerObservers;
    private List<ICollisionObserver> collisionObservers;

    private final SoundHandler soundHandler = new SoundHandler();
    private final HighScoreHandler scoreHandler = new HighScoreHandler();

    @Override
    public void start(Stage stage) throws Exception {
        gameWorld = GameWorld.getInstance();
        gameObjects = gameWorld.getGameObjects();
        Window window = new Window(stage, gameWorld.getPlayingFieldWidth(), gameWorld.getPlayingFieldHeight());
        GraphicsContext graphicsContext = window.getGraphicsContext();
        MainMenu mainMenu = new MainMenu();
        HighScoreMenu highScoreMenu = new HighScoreMenu();
        CharacterMenu characterMenu = new CharacterMenu();
        GameOverMenu gameOverMenu = new GameOverMenu();
        PauseMenu pauseMenu = new PauseMenu();

        GameObjectGUI gameObjectGUI = new GameObjectGUI(graphicsContext);
        HealthBarGUI healthBarGUI = new HealthBarGUI(graphicsContext);
        ShieldGUI shieldGUI = new ShieldGUI(graphicsContext);
        BackgroundView backgroundView = new BackgroundView(graphicsContext);
        ITimeObserver timeView = new TimeView(graphicsContext);

        gameLoop = new PausableAnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;
            int updateCounter = 60;

            final long animationNanoTime = System.nanoTime();

            @Override
            public void tick(long currentNanoTime) {
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

                /**
                 * projectile spawner
                 * @author Irja Vuorela
                 */

                updateCounter = updateCounter + 1;
                if (updateCounter >= 120) {
                    updateCounter = 0;
                    gameObjects.add(ProjectileFactory.createSmallAsteroid());
                    gameObjects.add(ProjectileFactory.createSmallAsteroid());
                    gameObjects.add(ProjectileFactory.createSmallAsteroid());
                    gameObjects.add(ProjectileFactory.createMediumAsteroid());
                    gameObjects.add(ProjectileFactory.createHealthPowerUp());
                    gameObjects.add(ProjectileFactory.createShieldPowerUp());
                }

                /**
                 * removes offscreen projectiles
                 * @author Irja Vuorela
                 */
                for (AbstractGameObject g : gameObjects) {
                    if (g instanceof Projectile) {
                        if (((Projectile) g).isNotOnScreen()) {
                            gameObjects.remove(g);
                            break;
                        }
                    }
                }
                gameWorld.wrapAround();

                long elapsedTime = calculateElapsedTime();
                notifyTimeObservers(elapsedTime, animationTime);

                endGame();
                previousNanoTime = currentNanoTime;
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
        KeyController keyController = new KeyController(stage, gameWorld.getSpaceships(), gameLoop, pauseMenu);
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
        playerObservers = new ArrayList<>();

        addObserver(gameObjectGUI);
        addObserver(window);
        addObserver(vc);
        addTimeObserver(timeView);
        addTimeObserver(shieldGUI);
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

    public void startAnimationTimer() {
        gameLoop.start();
    }

    //@Author Isak
    public void stopAnimationTimer() {
        gameLoop.stop();
    }

    public void pauseAnimationTimer() {
        gameLoop.pause();
    }

    public void playAnimationTimer() {
        gameLoop.play();
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

    private void endGame() { //TODO: Broken plz fix
        if (gameWorld.getPlayer().getHp() <= 0) {
            gameWorld.setGameOver(true);
            notifyGameOverObservers(gameWorld.getIsGameOver());
            gameObjects.clear();
            collisionObservers.remove(gameWorld.getPlayer());
            gameWorld.createNewGameWorld();
            gameWorld = GameWorld.getInstance();
            gameLoop.stop();
            collisionObservers.add(gameWorld.getPlayer());
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
    public long calculateElapsedTime() {
        long endNanoTime = System.nanoTime();
        return endNanoTime - gameLoop.getStartNanoTime();
    }

    @Override
    public void notifyGameOverObservers(boolean isGameOver) {
        for (IGameOverObserver obs : gameOverObservers) {
            obs.actOnEvent(isGameOver);
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
        Player copyOfPlayer = new Player(player.getSpaceships(), player.getNrOfShields(), player.getPoints(), player.getHp());
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
            obs.actOnEvent(gameObject);
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
