import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Entities.Player.SpaceshipGUI;
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

public class Window {

    //Creates Pane
    private final Pane win = new Pane();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    Spaceship spaceship = SpaceshipFactory.createSpaceship();
    SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 368, 268);
    Image spaceShipImage = spaceshipGUI.getImage();

    Scene gameScene;

    /*
    @Override
    public void start(Stage stage) {
        try {
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
                }
            }.start();
            //----------------------------------------------------------------------------------------------------------
            //Sets scene from created Pane createContent
            stage.setScene(new Scene(createContent()));
            //Removes option to change size of program window
            stage.setResizable(false);
            //Opens program window
            stage.show();

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

     */

    public Window(){
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
                }
            }.start();

            gameScene = new Scene(win);

            // Handle key pressed
            // @Author Irja Vuorela
            KeyController keyController = new KeyController(spaceship);
            gameScene.setOnKeyPressed(
                    event -> keyController.handleKeyPressed(event)
            );

            // Handle key released
            // @Author Irja Vuorela
            gameScene.setOnKeyReleased(
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

    // @Author Isak Almeros
    public Scene getGameScene(){
        return gameScene;
    }
}
