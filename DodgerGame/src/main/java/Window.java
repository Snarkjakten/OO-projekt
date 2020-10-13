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

public class Window implements IObservable, ISoundObservable {

    //Creates Pane
    private final Pane root = new Pane();
    private final Game game = Game.getInstance();
    private final Stage stage;
    private AnimationTimer animationTimer;

    private long startNanoTime;
    private List<IObserver> observers;
    private List<TimeObserver> timeObservers;
    private List<ISoundObserve> soundObservers;

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

            TimeObserver timeView = new TimeView(gc);
            timeObservers = new ArrayList<>();
            timeObservers.add(timeView);

            soundObservers = new ArrayList<>();
            soundObservers.add(soundHandler);

            //Adds ImageView and Canvas to Pane
            root.getChildren().addAll(canvas);

            startNanoTime = System.nanoTime();

            // Game loop --------------------------------------------------------------

            observers = new ArrayList<>();
            observers.add(gameObjectGUI);

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

                        // If spaceship was debuffed when the last game ended
                        game.getSpaceships().get(0).speed = 250;

                        List<AbstractMovable> removeProjectiles = new ArrayList<>();
                        List<AbstractMovable> removeSpaceships = new ArrayList<>();

                        // Removes projectiles
                        for (AbstractMovable gameObject : gameObjects) {
                            if (gameObject instanceof Projectile) {
                                removeProjectiles.add(gameObject);
                            }
                        }

                        gameObjects.removeAll(removeProjectiles);

                        // Removes wraparaound spaceships
                        for(int i = 1; i < gameObjects.size(); i++) {
                            removeSpaceships.add(gameObjects.get(i));
                        }

                        gameObjects.get(0).setPosition(368,268);
                        game.getSpaceships().get(0).resetDirection();

                        if (gameObjects.size() > 1) {
                            gameObjects.remove(1);
                            game.getSpaceships().remove(1);
                        }

                        gameObjects.removeAll(removeSpaceships);
                        game.getSpaceships().removeAll(removeSpaceships);

                        // Resets spaceships positions
                        game.getSpaceships().get(0).setPosition(368, 268);

                        restartScheduled = false;
                    }

                    // @Author Isak Almeros
                    // Resets speed if ten seconds has passed since player was debuffed
                    if(player.getSlowDebuffed() && calculateElapsedTime(player.getSlowDebuffedTime()) == 10) {
                        List<Spaceship> spaceships = player.getSpaceships();

                        for(Spaceship spaceship : spaceships) {
                            spaceship.speed = 250;
                        }

                        player.setSlowDebuffed(false);
                    }

                    /**
                     * Calculates time since last update
                     * @author Irja Vuorela
                     */
                    currentNanoTime = System.nanoTime();
                    double deltaTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                    double animationTime = (currentNanoTime - animationNanoTime) / 1000000000.0;

                    backgroundView.drawBackground(0, 0, 600, 800, 0); // TODO: Get height and width from model
                    healthBarGUI.drawHealthBar(game.getPlayer().getHp().doubleValue());

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
                        gameObjects.add(ProjectileFactory.createDebuff());
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

                    int elapsedTime = calculateElapsedTime(startNanoTime);
                    notifyTimeObeservers(elapsedTime);
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

    // todo:
    public int getPoints() {
        return player.getPoints();
    }

    // todo:
    public void setPoints() {
        player.setPoints(calculateElapsedTime(startNanoTime));
    }

    public void startAnimationTimer() {
        animationTimer.start();
    }

    public void stopAnimationTimer() {
        animationTimer.stop();
        restartScheduled = true;
    }

    public int calculateElapsedTime(long startTime) {
         long currentNanoTime = System.nanoTime();
         return (int) ((currentNanoTime - startTime) / 1000000000.0);
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

    public void notifyTimeObeservers(int time) {
        for (TimeObserver obs : timeObservers) {
            obs.actOnEvent(time);
        }
    }

    @Override
    public void removeSoundObserver(ISoundObserve iso) {
        this.soundObservers.remove(iso);
    }

    @Override
    public void addSoundObserver(ISoundObserve iso) {
        this.soundObservers.add(iso);
    }

    @Override
    public void notifySoundObservers(Class c) {
        for (ISoundObserve iso : soundObservers) {
            iso.actOnEvent(c);
        }
    }
}