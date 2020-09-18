import Entities.Projectiles.ProjectileFactory;
import Entities.Projectiles.ProjectileGUI;
import Entities.Ship;
import javafx.animation.AnimationTimer;
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

public class Window extends Application {

    // --- todo: flytta main från Window --------------------
    public static void main(String[] args) {
        launch(args);
    }
    Ship ship = new Ship();
    ProjectileGUI projectileGUI = new ProjectileGUI(ProjectileFactory.createMediumAsteroid());
    Image asteroidImage = projectileGUI.getImage();
    //-------------------------------------------------------


    //Creates Pane
    private final Pane win = new Pane();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);

    //Sets size of Pane
    private Pane createContent() {
        win.setPrefSize(800, 600);
        return win;
    }

    @Override
    public void start(Stage stage) {
        try {
            //Creates ImageView and sets image space.jpg as view
//            ImageView iV = new ImageView(windowBackground);
//            iV.setImage(windowBackground);


            //Sets image size to fit Pane size (hard coded for now)
//            iV.setFitHeight(600);
//            iV.setFitWidth(800);

            //Adds ImageView to Pane
//            win.getChildren().addAll(iV);

            //Sets scene from created Pane createContent
            stage.setScene(new Scene(createContent()));
            //Removes option to change size of progam window
            stage.setResizable(false);
            //Opens program window



            // Handle key pressed
            // @Author Irja Vuorela
            KeyController keyController = new KeyController(ship);
            stage.getScene().setOnKeyPressed(
                    event -> keyController.handleKeyPressed(event)
            );

            // Handle key release
            // @Author Irja Vuorela
            stage.getScene().setOnKeyReleased(
                    event -> keyController.handleKeyReleased(event)
            );
            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            win.getChildren().addAll(canvas);


            final long startNanoTime = System.nanoTime();

            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    projectileGUI.getProjectile().move();
                    gc.drawImage(windowBackground, 0, 0, 800, 600);
                    gc.drawImage(asteroidImage, projectileGUI.getHorizontalPosition(), projectileGUI.getVerticalPosition());

                    if (projectileGUI.getProjectile().isNotOnScreen()) {
                        projectileGUI = new ProjectileGUI(ProjectileFactory.createMediumAsteroid());
                    }

//                    if (x > 950 || x < -150) {
//                        projectileGUI = new ProjectileGUI(ProjectileFactory.createMediumAsteroid());
//                    } else if (y > 600 || y < 0) {
//                        projectileGUI = new ProjectileGUI(ProjectileFactory.createMediumAsteroid());
//                    }
                }
            } .start();

            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
