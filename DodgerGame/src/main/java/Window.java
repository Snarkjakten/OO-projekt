import Entities.LaserBeam;
import Entities.Player.Spaceship;
import Entities.Projectiles.*;
import Movement.AbstractMovable;
import javafx.animation.AnimationTimer;
import Entities.Projectiles.ProjectileFactory;
import Entities.Projectiles.ProjectileGUI;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;

/*
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window implements IObservable {

    //Creates Pane
    private final Pane root = new Pane();
    Game game = Game.getInstance();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    Spaceship spaceship = game.getSpaceship();
    Spaceship wrapAroundSpaceship = game.getWrapAroundSpaceship();
    Image spaceShipImage = game.getSpaceship().getImage();

    private Stage stage;
    private AnimationTimer animationTimer;

    long startNanoTime;
    long endNanoTime;
    private int points;
    private List<IObserver> observers;
    private List<AbstractMovable> gameObjects;

    ProjectileGUI projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
    Image asteroidImage = projectileGUI.getImage();
    ProjectileGUI healthGain = new ProjectileGUI(ProjectileFactory.createHealthPowerUp());
    Image health = healthGain.getImage();
    ProjectileGUI shieldGUI = new ProjectileGUI(ProjectileFactory.createShieldPowerUp());
    Image shieldImage = shieldGUI.getImage();
    LaserBeam laserBeam = new LaserBeam(300, 0.1, true);

    public Window(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        try {
            createContent();

            spaceship.setHp(200);

            // @Author Tobias Engblom
            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            //Adds ImageView and Canvas to Pane
            root.getChildren().addAll(canvas);

            startNanoTime = System.nanoTime();

            // Game loop --------------------------------------------------------------

            observers = new ArrayList<>();

            // Adds spaceship (and wraparound counterpart) to list of game objects
            gameObjects = new ArrayList<>();
            gameObjects.add(spaceship);
            gameObjects.add(wrapAroundSpaceship);

            animationTimer = new AnimationTimer() {
                long currentNanoTime = System.nanoTime();
                long previousNanoTime = currentNanoTime;
                int updateCounter = 60;

                final long animationNanoTime = System.nanoTime();

                @Override
                public void handle(long currentNanoTime) {

                    // Calculate time since last update
                    // @author Irja Vuorela
                    currentNanoTime = System.nanoTime();
                    double deltaTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                    double animationTime = (currentNanoTime - animationNanoTime) / 1000000000.0;

                    // todo: move drawImage from game loop to a view with observer
                    gc.drawImage(windowBackground, 0, 0, 800, 600);

                    gc.drawImage(laserBeam.getFrame(animationTime), laserBeam.getHorizontal(), laserBeam.getVertical());


                    // update positions and notify observers
                    // @author Irja vuorela
                    for (AbstractMovable gameObject : gameObjects) {
                        gameObject.move(deltaTime);
                        notifyObservers(gameObject.position.getX(), gameObject.position.getY());
                        // todo: move to view/observer
                        if (gameObject instanceof MediumAsteroid) {
                            gc.drawImage(asteroidImage, gameObject.position.getX(), gameObject.position.getY(), 128, 128);
                        }
                        if (gameObject instanceof SmallAsteroid) {
                            gc.drawImage(asteroidImage, gameObject.position.getX(), gameObject.position.getY(), 64, 64);
                        }
                        if (gameObject instanceof Spaceship) {
                            gc.drawImage(spaceShipImage, gameObject.position.getX(), gameObject.position.getY(), 64, 64);
                        }
                        if (gameObject instanceof HealthPowerUp) {
                            gc.drawImage(health, gameObject.position.getX(), gameObject.position.getY(), 64, 64);
                        }
                        if (gameObject instanceof ShieldPowerUp) {
                            gc.drawImage(shieldImage, gameObject.position.getX(), gameObject.position.getY(), 64, 64);
                        }
                    }

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
                        if (g instanceof Projectile){
                            if (((Projectile) g).isNotOnScreen()){
                                gameObjects.remove(g);
                                break;
                            }
                        }
                    }


                    game.wrapAround();
                    previousNanoTime = currentNanoTime;


                }
            };

            animationTimer.start();

            // Handle key pressed
            // @Author Irja Vuorela
            KeyController keyController = new KeyController(game.getSpaceships());
            stage.getScene().setOnKeyPressed(
                    event -> keyController.handleKeyPressed(event));

            // Handle key released
            // @Author Irja Vuorela
            stage.getScene().setOnKeyReleased(
                    event -> keyController.handleKeyReleased(event)
            );

            // TODO: 2020-09-26 replace onMouseClicked with collision
            stage.getScene().setOnMouseClicked(event -> {
                SimpleIntegerProperty damage = new SimpleIntegerProperty(100);
                NumberBinding subtraction = spaceship.getHp().subtract(damage);
                spaceship.setHp(subtraction.intValue());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Sets size of Pane
    private Pane createContent() {
        root.setPrefSize(800, 600);
        return root;
    }

    public Pane getRoot() {
        return root;
    }

    public int getPoints(){
        return points;
    }


    // @Author Isak Almeros
    public void stopAnimationTimer(){
        endNanoTime = System.nanoTime();
        points = (int)((endNanoTime - startNanoTime)/1000000000.0);
        animationTimer.stop();
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
    public void notifyObservers(double x, double y) {
        for (IObserver obs : observers) {
            obs.actOnEvent(x, y);

        }
    }
}