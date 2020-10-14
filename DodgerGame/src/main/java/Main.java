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
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements IPlayingFieldObservable, IGameObjectObservable, ISoundObservable, IHealthObservable, ITimeObservable, IGameOverObservable {

    private GameWorld gameWorld;

    private List<AbstractGameObject> gameObjects;

    private SoundHandler soundHandler = new SoundHandler();
    private HighScoreHandler scoreHandler = new HighScoreHandler();

    private GraphicsContext graphicsContext;

    boolean restartScheduled = false;

    private AnimationTimer gameLoop;
    private long startNanoTime;
    private List<IGameObjectObserver> gameObjectObservers;
    private List<ITimeObserver> timeObservers;
    private List<ISoundObserve> soundObservers;
    private List<IHealthObserver> healthObservers;
    private List<IGameOverObserver> gameOverObservers;
    private List<IPlayingFieldObserver> playingFieldObservers;

    @Override
    public void start(Stage stage) throws Exception {
        gameWorld = GameWorld.getInstance();
        gameObjects = gameWorld.getGameObjects();
        Window window = new Window(stage, gameWorld.getPlayingFieldWidth(), gameWorld.getPlayingFieldHeight());
        graphicsContext = window.getGraphicsContext();
        MainMenu mainMenu = new MainMenu();
        HighScoreMenu highScoreMenu = new HighScoreMenu();
        CharacterMenu characterMenu = new CharacterMenu();
        GameOverMenu gameOverMenu = new GameOverMenu();
        gameOverObservers = new ArrayList<>();
        gameObjectObservers = new ArrayList<>();
        playingFieldObservers = new ArrayList<>();
        long startNanoTime = System.nanoTime(); //TODO: this gives wrong time in game

        gameLoop = new AnimationTimer() {
            //TODO: new game?

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
                    for(AbstractGameObject a : gameObjects){
                        if(collisionHandler.checkCollision(gameObject, a) && !gameObject.getCollided() && !a.getCollided()){
                            if(a instanceof Spaceship) {
                                notifySoundObservers(gameObject.getClass());
                                toBeRemoved.add(gameObject);
                            } else if(gameObject instanceof Spaceship){
                                notifySoundObservers(a.getClass());
                                toBeRemoved.add(a);
                            }
                            collisionHandler.collide(a, gameObject);
                        }
                    }
                }

                for(AbstractGameObject a : toBeRemoved){
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
                notifyTimeObservers(elapsedTime);
                notifyHealthObservers(gameWorld.getPlayer().getHp());

                endGame();
                previousNanoTime = currentNanoTime;
            }
        };
        GameObjectGUI gameObjectGUI = new GameObjectGUI(graphicsContext);
        ViewController vc = new ViewController(window, mainMenu, highScoreMenu , characterMenu, gameOverMenu, stage, gameLoop, gameObjectGUI);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();
        window.init();

        //@Author tobbe
        HealthBarGUI healthBarGUI = new HealthBarGUI(graphicsContext);
        ShieldGUI shieldGUI = new ShieldGUI(graphicsContext);
        addObserver(gameObjectGUI);
        addObserver(window);
        addObserver(vc);

        BackgroundView backgroundView = new BackgroundView(graphicsContext);
        addObserver(backgroundView);

        ITimeObserver timeView = new TimeView(graphicsContext);
        timeObservers = new ArrayList<>();
        addObserver(timeView);

        soundObservers = new ArrayList<>();
        addObserver(soundHandler);

        healthObservers = new ArrayList<>();
        addObserver(healthBarGUI);


        soundHandler.musicPlayer(GameObjectsSounds.getBackgroundMusicPath());

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void checkGameWorld(){
        this.gameWorld = GameWorld.getInstance();
    }

    //@Author Isak
    public void stopAnimationTimer() {
        gameLoop.stop();
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
    public void notifyHealthObservers(int health) {
        for(IHealthObserver obs : healthObservers) {
            obs.actOnEvent(health);
        }
    }

    @Override
    public void addObserver(IHealthObserver obs) {
        this.healthObservers.add(obs);
    }

    @Override
    public void removeObserver(IHealthObserver obs) {
        this.healthObservers.remove(obs);
    }

    @Override
    public void notifyTimeObservers(long time) {
        for (ITimeObserver obs : timeObservers) {
            obs.actOnEvent(time);
        }
    }

    @Override
    public void addObserver(ITimeObserver obs) {
        this.timeObservers.add(obs);
    }

    @Override
    public void removeObserver(ITimeObserver obs) {
        this.timeObservers.remove(obs);
    }

    private void endGame() { //TODO: Broken plz fix
        if(gameWorld.getPlayer().getHp() <= 0) {
            gameWorld.setGameOver(true);
            notifyGameOverObservers(gameWorld.getIsGameOver());
            gameObjects.clear();
            gameWorld.createNewGameWorld();
            gameWorld = GameWorld.getInstance();
            gameLoop.stop();

        }
    }

    @Override
    public void notifyPlayingFieldObservers(double width, double height) {
        for(IPlayingFieldObserver obs : playingFieldObservers) {
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
        return endNanoTime - startNanoTime;
    }

    @Override
    public void notifyGameOverObservers(boolean isGameOver) {
        for(IGameOverObserver obs : gameOverObservers){
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
}
