import Entities.Player.Spaceship;
import javafx.animation.AnimationTimer;
import Entities.Projectiles.ProjectileFactory;
import Entities.Projectiles.ProjectileGUI;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
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
    Image spaceShipImage = game.getSpaceships().get(0).getImage();

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
                    for (Spaceship spaceship : game.getSpaceships())
                        gc.drawImage(spaceShipImage, spaceship.position.getX(), spaceship.position.getY(), 64, 64);
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
}
