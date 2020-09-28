import Entities.Player.Spaceship;
import Entities.Projectiles.*;
import Movement.AbstractMovable;
import javafx.animation.AnimationTimer;

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
    private final Pane win = new Pane();
    Game game = Game.getInstance();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    Spaceship spaceship = game.getSpaceship();
    Spaceship wrapAroundSpaceship = game.getWrapAroundSpaceship();
    Image spaceShipImage = game.getSpaceship().getImage();

    private Stage stage;
    private List<IObserver> observers;
    private List<AbstractMovable> gameObjects;

    ProjectileGUI projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
    Image asteroidImage = projectileGUI.getImage();

    public Window(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        try {
            createContent();

            // @Author Tobias Engblom
            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            //Adds ImageView and Canvas to Pane
            win.getChildren().addAll(canvas);

            // Game loop --------------------------------------------------------------

            observers = new ArrayList<>();

            // Adds spaceship (and wraparound counterpart) to list of game objects
            gameObjects = new ArrayList<>();
            gameObjects.add(spaceship);
            gameObjects.add(wrapAroundSpaceship);
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createMediumAsteroid());


            new AnimationTimer() {
                long currentNanoTime = System.nanoTime();
                long previousNanoTime = currentNanoTime;
                int updateCounter = 60;

                @Override
                public void handle(long currentNanoTime) {

                    // Calculate time since last update
                    // @author Irja Vuorela
                    currentNanoTime = System.nanoTime();
                    double deltaTime = (currentNanoTime - previousNanoTime) / 1000000000.0;

                    // todo: move drawImage from game loop to a view with observer
                    gc.drawImage(windowBackground, 0, 0, 800, 600);


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
                            gc.drawImage(spaceShipImage, spaceship.position.getX(), spaceship.position.getY(), 64, 64);
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
                    }

                    // todo: find out why this is needed (wrapShip's position is wrong without it)
                    gc.drawImage(spaceShipImage, wrapAroundSpaceship.position.getX(), wrapAroundSpaceship.position.getY(), 64, 64);

                    /* todo: lots of errors
                    // remove offscreen projectiles
                    // @author Irja Vuorela
                    for (AbstractMovable g : gameObjects) {
                        if (g instanceof Projectile){
                            if (((Projectile) g).isNotOnScreen()){
                                gameObjects.remove(g);
                            }
                        }
                    }
                    */

                    game.wrapAround();
                    previousNanoTime = currentNanoTime;


                }
            }.start();

            // Handle key pressed
            // @Author Irja Vuorela
            KeyController keyController = new KeyController(game.getSpaceships());
            stage.getScene().setOnKeyPressed(
                    event -> keyController.handleKeyPressed(event)
            );

            // Handle key released
            // @Author Irja Vuorela
            stage.getScene().setOnKeyReleased(
                    event -> keyController.handleKeyReleased(event)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Sets size of Pane
    private Pane createContent() {
        win.setPrefSize(800, 600);
        return win;
    }

    public Pane getWin() {
        return win;
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