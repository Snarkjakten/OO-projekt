import Entities.LaserBeam;
import Entities.Player.Player;
import Entities.Player.Spaceship;
import Entities.Projectiles.CollisionHandler;
import Entities.Projectiles.Projectile;
import Entities.Projectiles.ProjectileFactory;
import Movement.AbstractMovable;
import Score.HighScoreHandler;
import View.*;
import View.Sound.ISoundObservable;
import View.Sound.ISoundObserve;
import View.Sound.SoundHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window implements IObservable, ISoundObservable, IHealthObservable, ITimeObservable {

    //Creates Pane
    private final Pane root = new Pane();
    private final Game game = Game.getInstance();
    private final Stage stage;
    private AnimationTimer animationTimer;

    private long startNanoTime;
    private List<IObserver> observers;
    private List<ITimeObserver> timeObservers;
    private List<ISoundObserve> soundObservers;
    private List<IHealthObserver> healthObservers;
    private List<IGameOverObserver> gameOverObservers;

    SoundHandler soundHandler = new SoundHandler();

    private HighScoreHandler scoreHandler = new HighScoreHandler();

    protected Player player = game.getPlayer();
    private final List<AbstractMovable> gameObjects = game.getGameObjects();

    private LaserBeam laserBeam = new LaserBeam();
    private double animationDuration = 0.1;

    private Boolean restartScheduled = false;

    public Window(Stage stage) {
        this.stage = stage;
    }

    public void init(String imageName) {
        try {
            createContent();

            player.setHp(200);

            // @Author Tobias Engblom
            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            GameObjectGUI gameObjectGUI = new GameObjectGUI(gc, imageName);
            LaserGUI laserGUI = new LaserGUI(gc, animationDuration, laserBeam.isVertical());
            ShieldGUI shieldGUI = new ShieldGUI(gc, animationDuration);
            BackgroundView backgroundView = new BackgroundView(gc);
            HealthBarGUI healthBarGUI = new HealthBarGUI(gc);
            //TODO: add viewcontroller when moved

            ITimeObserver timeView = new TimeView(gc);
            timeObservers = new ArrayList<>();
            addObserver(timeView);

            soundObservers = new ArrayList<>();
            addObserver(soundHandler);

            healthObservers = new ArrayList<>();
            addObserver(healthBarGUI);

            //TODO: see above
            /*gameOverObservers = new ArrayList<>();
            addObserver();*/


            //Adds ImageView and Canvas to Pane
            root.getChildren().addAll(canvas);

            startNanoTime = System.nanoTime();

            // Game loop --------------------------------------------------------------

            observers = new ArrayList<>();
            addObserver(gameObjectGUI);

            animationTimer = new AnimationTimer() {
                final long currentNanoTime = System.nanoTime();
                long previousNanoTime = currentNanoTime;
                int updateCounter = 60;

                long animationNanoTime = System.nanoTime();

                @Override
                public void handle(long currentNanoTime) {


                    // Removes projectiles and resets the spaceships positions when the game is restarted
                    // @Author Isak Almeros
                    if (restartScheduled) {

                        List<AbstractMovable> removeProjectiles = new ArrayList<>();

                        for (AbstractMovable gameObject : gameObjects) {
                            if (gameObject instanceof Projectile) {
                                removeProjectiles.add(gameObject);
                            }
                        }

                        gameObjects.removeAll(removeProjectiles);

                        gameObjects.get(0).setPosition(368,268);
                        game.getSpaceships().get(0).resetDirection();

                        if (gameObjects.size() > 1) {
                            gameObjects.remove(1);
                            game.getSpaceships().remove(1);
                        }

                        restartScheduled = false;
                    }

                    /**
                     * Calculates time since last update
                     * @author Irja Vuorela
                     */
                    currentNanoTime = System.nanoTime();
                    double deltaTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                    double animationTime = (currentNanoTime - animationNanoTime) / 1000000000.0;

                    backgroundView.drawBackground(0, 0, 600, 800, 0); // TODO: Get height and width from model


                    laserBeam.move(deltaTime);
                    laserGUI.drawLaser(animationTime, laserBeam.position.getX(), laserBeam.position.getY());

                    /**
                     * update positions and notify observers
                     * @author Irja vuorela
                     */
                    for (AbstractMovable gameObject : gameObjects) {
                        gameObject.move(deltaTime);
                        notifyObservers(gameObject.position.getX(), gameObject.position.getY(), gameObject.getClass(), gameObject.getHeight(), gameObject.getWidth());
                    }

                    /**
                     * Creates lists to store objects to be removed
                     * Sets boolean collided to true for reading single impact
                     * Calls collide method on items collided
                     * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
                     */

                    List<AbstractMovable> toBeRemoved;
                    toBeRemoved = new ArrayList<>();

                    CollisionHandler collisionHandler = new CollisionHandler();

                    for (AbstractMovable gameObject : gameObjects) {
                        notifyObservers(gameObject.position.getX(), gameObject.position.getY(), gameObject.getClass(), gameObject.getHeight(), gameObject.getWidth());
                        for(AbstractMovable a : gameObjects){
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

                    for(AbstractMovable a : toBeRemoved){
                        gameObjects.remove(a);
                    }

                    //End of collision handling -----------------------------------

                    shieldGUI.drawImage(player, animationTime);


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
                    for (AbstractMovable g : gameObjects) {
                        if (g instanceof Projectile) {
                            if (((Projectile) g).isNotOnScreen()) {
                                gameObjects.remove(g);
                                break;
                            }
                        }
                    }
                    game.wrapAround();
                    previousNanoTime = currentNanoTime;

                    long elapsedTime = calculateElapsedTime();
                    notifyTimeObservers(elapsedTime);
                    notifyHealthObservers(player.getHp());

                    endGame();
                }
            };

            /**
             * Handle key pressed
             * @Author Irja Vuorela
             */
            KeyController keyController = new KeyController(game.getSpaceships());
            stage.getScene().setOnKeyPressed(
                    keyController::handleKeyPressed);

            /**
             * Handle key released
             * @Author Irja Vuorela
             */
            stage.getScene().setOnKeyReleased(
                    keyController::handleKeyReleased
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Sets size of Pane
    private void createContent() {
        root.setPrefSize(800, 600);
    }

    public Pane getRoot() {
        return root;
    }

    public void startAnimationTimer() {
        animationTimer.start();
    }

    public void stopAnimationTimer() {
        animationTimer.stop();
        restartScheduled = true;
    }

    // Calculates elapsed time
    public long calculateElapsedTime() {
        long endNanoTime = System.nanoTime();
        return endNanoTime - startNanoTime;
    }

    /**
     * @author Irja Vuorela
     */
    @Override
    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

    /**
     * @author Irja Vuorela
     */
    @Override
    public void removeObserver(IObserver obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers(double x, double y, Class c, double height, double width) {
        for (IObserver obs : observers) {
            obs.actOnEvent(x, y, c, height, width);
        }
    }

    @Override
    public void removeObserver(ISoundObserve iso) {
        this.soundObservers.remove(iso);
    }

    @Override
    public void addObserver(ISoundObserve iso) {
        this.soundObservers.add(iso);
    }

    @Override
    public void notifySoundObservers(Class c) {
        for (ISoundObserve iso : soundObservers) {
            iso.actOnEvent(c);
        }
    }

    @Override
    public void notifyHealthObservers(int health) {
        for(IHealthObserver iho : healthObservers) {
            iho.actOnEvent(health);
        }
    }

    @Override
    public void addObserver(IHealthObserver iho) {
        this.healthObservers.add(iho);
    }

    @Override
    public void removeObserver(IHealthObserver iho) {
        this.healthObservers.remove(iho);
    }

    @Override
    public void notifyTimeObservers(long time) {
        for (ITimeObserver obs : timeObservers) {
            obs.actOnEvent(time);
        }
    }

    @Override
    public void addObserver(ITimeObserver ito) {
        this.timeObservers.add(ito);
    }

    @Override
    public void removeObserver(ITimeObserver ito) {
        this.timeObservers.remove(ito);
    }

    private void endGame() {
        if(player.getHp() <= 0) {
            game.setGameOver(true);
        }
    }
}