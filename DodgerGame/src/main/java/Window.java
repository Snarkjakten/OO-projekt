import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Entities.Player.SpaceshipGUI;
import Entities.Projectiles.Projectile;
import javafx.animation.AnimationTimer;
import Entities.Projectiles.ProjectileFactory;
import Entities.Projectiles.ProjectileGUI;
import javafx.application.Application;
import javafx.scene.Scene;
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
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    Spaceship spaceship = SpaceshipFactory.createSpaceship();
    SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 368, 268);
    Image spaceShipImage = spaceshipGUI.getImage();

    private List<IObserver> observers;
    Projectile[] projectiles = {
            ProjectileFactory.createSmallAsteroid(),
            ProjectileFactory.createSmallAsteroid(),
            ProjectileFactory.createSmallAsteroid(),
            ProjectileFactory.createMediumAsteroid(),
            ProjectileFactory.createMediumAsteroid()
    };

    private Stage stage;

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

            observers = new ArrayList<>();
            long currentNanoTime = System.nanoTime();

            new AnimationTimer() {
                long previousNanoTime = currentNanoTime;

                @Override
                public void handle(long currentNanoTime) {

                    // calculate time since last update
                    // @author Irja Vuorela
                    currentNanoTime = System.nanoTime();
                    double deltaTime = (currentNanoTime - previousNanoTime) / 1000000000.0;

                    gc.drawImage(windowBackground, 0, 0, 800, 600);

                    /*
                    gc.drawImage(spaceShipImage, spaceshipGUI.getXPosition(), spaceshipGUI.getYPosition(), 64, 64);
                    gc.drawImage(asteroidImage, projectileGUI.getHorizontalPosition(), projectileGUI.getVerticalPosition());
                    projectileGUI.getProjectile().move();
                    if (projectileGUI.getProjectile().isNotOnScreen()) {
                        projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());}
                    */

                    // update positions and broadcast to observers
                    // @author Irja Vuorela
                    spaceship.move(deltaTime);
                    notifyObservers(spaceship.position.getX(), spaceship.position.getY());
                    // todo: move drawImage somewhere with an observer
                    gc.drawImage(spaceShipImage, spaceship.position.getX(), spaceship.position.getY(), 64, 64);

                    // @author Irja Vuorela
                    for (Projectile p : projectiles) {
                        p.move(deltaTime);
                        notifyObservers(p.position.getX(), p.position.getY());
                        // todo: move drawImage somewhere with an observer and draw correct image for type of projectile
                        gc.drawImage(asteroidImage, p.position.getX(), p.position.getY());
                    }

                    previousNanoTime = currentNanoTime;


                }
            }.start();

            // Handle key pressed
            // @Author Irja Vuorela
            KeyController keyController = new KeyController(spaceship);
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

    // add an observer to the list of observers
    // @Author Irja Vuorela
    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);

    }

    // remove an observer from the list of observers
    // @Author Irja Vuorela
    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);

    }

    // notify coordinates to observers
    // @autor Irja Vuorela
    @Override
    public void notifyObservers(double x, double y) {
        for (IObserver observer : observers) {
            observer.actOnEvent(x, y);
        }

    }
}
