import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Entities.Player.SpaceshipGUI;
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

public class Window {

    //Creates Pane
    private final Pane win = new Pane();
    Game game = Game.getInstance();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    SpaceshipGUI spaceshipGUI = game.getSpaceshipGUI();
    SpaceshipGUI wrapAroundSpaceshipGUI = game.getWrapAroundSpaceshipGUI();
    Image spaceShipImage = spaceshipGUI.getImage();
    List<Spaceship> spaceships = game.getSpaceships();

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

            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    gc.drawImage(windowBackground, 0, 0, 800, 600);
                    gc.drawImage(spaceShipImage, spaceshipGUI.getXPosition(), spaceshipGUI.getYPosition(), 64, 64);
                    gc.drawImage(spaceShipImage, wrapAroundSpaceshipGUI.getXPosition(), wrapAroundSpaceshipGUI.getYPosition(), 64, 64);
                    game.wrapAround();
                    gc.drawImage(asteroidImage, projectileGUI.getHorizontalPosition(), projectileGUI.getVerticalPosition());
                    projectileGUI.getProjectile().move();
                    if (projectileGUI.getProjectile().isNotOnScreen()) {
                        projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
                    }
                }
            }.start();

            // Handle key pressed
            // @Author Irja Vuorela
            KeyController keyController = new KeyController(spaceships);
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
}
