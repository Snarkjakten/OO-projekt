import Entities.Player.Spaceship;
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
import java.util.List;

/*
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window {

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

    ProjectileGUI projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
    Image asteroidImage = projectileGUI.getImage();

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

            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    gc.drawImage(windowBackground, 0, 0, 800, 600);
                    gc.drawImage(spaceShipImage, spaceship.position.getX(), spaceship.position.getY(), 64, 64);
                    gc.drawImage(spaceShipImage, wrapAroundSpaceship.position.getX(), wrapAroundSpaceship.position.getY(), 64, 64);
                    game.wrapAround();
                    gc.drawImage(asteroidImage, projectileGUI.getHorizontalPosition(), projectileGUI.getVerticalPosition());
                    projectileGUI.getProjectile().move();
                    if (projectileGUI.getProjectile().isNotOnScreen()) {
                        projectileGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
                    }
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
}
