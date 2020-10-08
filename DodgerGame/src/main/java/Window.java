import Entities.LaserBeam;
import Entities.Player.Player;
import Entities.Projectiles.Projectile;
import Entities.Projectiles.ProjectileFactory;
import Movement.AbstractMovable;
import View.*;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import View.BackgroundView;
import View.GameObjectGUI;
import View.HealthBar;
import View.IObserver;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window implements IObservable {

    //Creates Pane
    private final Pane root = new Pane();
    private final Game game = Game.getInstance();
    //Gets image from resources
    private final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    private final Image windowBackground;

    HealthBar healthBar = new HealthBar();
    Image hpBackground = healthBar.addBackgroundToHpBar();
    Image hpForeground = healthBar.addForegroundToHpBar();
    Image hpBorder = healthBar.addBorderToHpBar();

    {
        assert inputStream != null;
        windowBackground = new Image(inputStream);
    }

    private final Stage stage;
    protected PausableAnimationTimer pausableAnimationTimer;

    private long startNanoTime;
    private List<IObserver> observers;
    private List<TimeObserver> timeObservers;

    protected Player player = game.getPlayer();
    private final List<AbstractMovable> gameObjects = game.getGameObjects();
    private List<BackgroundView> backgrounds;


    private LaserBeam laserBeam = new LaserBeam();
    private double animationDuration = 0.1;

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

            TimeObserver timeView = new TimeView(gc);
            timeObservers = new ArrayList<>();
            timeObservers.add(timeView);

            //Adds ImageView and Canvas to Pane
            root.getChildren().addAll(canvas);

            startNanoTime = System.nanoTime();

            // Game loop --------------------------------------------------------------

            observers = new ArrayList<>();
            observers.add(gameObjectGUI);

            pausableAnimationTimer = new PausableAnimationTimer() {
                final long currentNanoTime = System.nanoTime();
                long previousNanoTime = currentNanoTime;
                int updateCounter = 60;

                final long animationNanoTime = System.nanoTime();

                @Override
                public void tick(long currentNanoTime) {

                    // Removes projectiles and resets the spaceships positions when the game is restarted
                    // @Author Isak Almeros
                    //TODO Change this if case
                    if (pausableAnimationTimer.isRestartScheduled()) {

                        List<AbstractMovable> removeProjectiles = new ArrayList<>();

                        for (AbstractMovable gameObject : gameObjects) {
                            if (gameObject instanceof Projectile) {
                                removeProjectiles.add(gameObject);
                            }
                        }

                        gameObjects.removeAll(removeProjectiles);

                        gameObjects.get(0).setPosition(368, 268);

                        if (gameObjects.size() > 1) {
                            gameObjects.remove(1);
                            game.getSpaceships().remove(1);
                        }
                    }

                    // Calculate time since last update
                    // @author Irja Vuorela
                    currentNanoTime = System.nanoTime();
                    double deltaTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                    double animationTime = (currentNanoTime - animationNanoTime) / 1000000000.0;

                    // todo: move drawImage from game loop to a view with observer
                    gc.drawImage(windowBackground, 0, 0, 800, 600);


                    gc.drawImage(hpBackground, 0, 0, 200, 40);
                    gc.drawImage(hpForeground, 0, 0, game.getPlayer().getHp().doubleValue(), 40);
                    gc.drawImage(hpBorder, 0, 0, 200, 40);


                    laserBeam.move(deltaTime);
                    laserGUI.drawLaser(animationTime, laserBeam.position.getX(), laserBeam.position.getY());


                    // update positions and notify observers
                    // @author Irja vuorela
                    for (AbstractMovable gameObject : gameObjects) {
                        gameObject.move(deltaTime);
                        notifyObservers(gameObject.position.getX(), gameObject.position.getY(), gameObject.getClass(), gameObject.getHeight(), gameObject.getWidth());
                    }
                    shieldGUI.drawImage(player, animationTime);


                    // projectile spawner
                    // @author Irja Vuorela
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

                    // remove offscreen projectiles
                    // @author Irja Vuorela
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

                    int elapsedTime = calculateElapsedTime();
                    notifyTimeObeservers(elapsedTime);
                }
            };

            // Handle key pressed
            // @Author Irja Vuorela
            KeyController keyController = new KeyController(game.getSpaceships());
            stage.getScene().setOnKeyPressed(
                    keyController::handleKeyPressed);

            // Handle key released
            // @Author Irja Vuorela
            stage.getScene().setOnKeyReleased(
                    keyController::handleKeyReleased
            );

            // TODO: 2020-09-26 replace onMouseClicked with collision
            stage.getScene().setOnMouseClicked(event -> {
                SimpleIntegerProperty damage = new SimpleIntegerProperty(100);
                NumberBinding subtraction = player.getHp().subtract(damage);
                player.setHp(subtraction.intValue());
            });

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

    public int getPoints() {
        return player.getPoints();
    }

    public void setPoints() {
        player.setPoints(calculateElapsedTime());
    }

    public void startAnimationTimer() {
        pausableAnimationTimer.start();
    }

    public void stopAnimationTimer() {
        pausableAnimationTimer.stop();
    }

    public void pauseAnimationTimer() {
        pausableAnimationTimer.pause();
    }

    // Calculates elapsed time in the game in seconds
    public int calculateElapsedTime() {
        long endNanoTime = System.nanoTime();
        return (int) ((endNanoTime - startNanoTime) / 1000000000.0);
    }

    @Override
    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

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
}