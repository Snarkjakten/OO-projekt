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

/*
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window {

    //Creates Pane
    private final Pane win = new Pane();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    Spaceship spaceship = SpaceshipFactory.createSpaceship();
    SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 368, 268);
    Image spaceShipImage = spaceshipGUI.getImage();

    private Stage stage;

    ProjectileGUI projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
    Image asteroidImage = projectileGUI.getImage();
    ProjectileGUI healthGain = new ProjectileGUI(ProjectileFactory.createHealthPowerUp());
    Image health = healthGain.getImage();
    ProjectileGUI shieldGUI = new ProjectileGUI(ProjectileFactory.createShieldPowerUp());
    Image shieldImage = shieldGUI.getImage();

    public Window(Stage stage){
        this.stage = stage;
    }

    public void init(){
        try {
            createContent();

            // @Author Tobias Engblom
            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            //Adds ImageView and Canvas to Pane
            win.getChildren().addAll(canvas);

            final long startNanoTime = System.nanoTime();

            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    gc.drawImage(windowBackground, 0, 0, 800, 600);
                    gc.drawImage(spaceShipImage, spaceshipGUI.getXPosition(), spaceshipGUI.getYPosition(), 64, 64);
                    gc.drawImage(asteroidImage, projectileGUI.getHorizontalPosition(), projectileGUI.getVerticalPosition());
                    gc.drawImage(health, healthGain.getHorizontalPosition(), healthGain.getVerticalPosition());
                    gc.drawImage(shieldImage, shieldGUI.getHorizontalPosition(), shieldGUI.getVerticalPosition(), 64, 64);
                    projectileGUI.getProjectile().move();
                    healthGain.getProjectile().move();
                    shieldGUI.getProjectile().move();
                    if (projectileGUI.getProjectile().isNotOnScreen()) {
                        projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
                    }
                    if (healthGain.getProjectile().isNotOnScreen()) {
                        healthGain = new ProjectileGUI(ProjectileFactory.createHealthPowerUp());
                    }
                    if (shieldGUI.getProjectile().isNotOnScreen()) {
                        shieldGUI = new ProjectileGUI(ProjectileFactory.createHealthPowerUp());
                    }

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
}
