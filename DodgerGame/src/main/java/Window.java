import Entities.Spaceship.Spaceship;
import Entities.Spaceship.SpaceshipFactory;
import Entities.Spaceship.SpaceshipGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    //-------------------------------------------------------


    //Creates Pane
    private final Pane win = new Pane();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    Spaceship spaceship = SpaceshipFactory.createSpaceship();
    SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 400, 300);

    //Sets size of Pane
    private Pane createContent() {
        win.setPrefSize(800, 600);
        return win;
    }

    @Override
    public void start(Stage stage) {
        try {
            //Creates ImageView and sets image space.jpg as view
            ImageView iV = new ImageView(windowBackground);

            //Sets image size to fit Pane size (hard coded for now)
            iV.setFitHeight(600);
            iV.setFitWidth(800);

            /**
             * @Author Tobias Engblom
             */
            Image spaceShipImage = spaceshipGUI.getImage();
            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(spaceShipImage, spaceshipGUI.getXPosition(), spaceshipGUI.getYPosition());
            System.out.println(spaceshipGUI.getPoint().getX() + " " + spaceshipGUI.getPoint().getY());
            System.out.println(spaceship.position.getX() + " " + spaceship.position.getY());
            //----------------------------------------------------------------------------------------------------------

            //Adds ImageView and Canvas to Pane
            win.getChildren().addAll(iV, canvas);

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

            // Handle key release
            // @Author Irja Vuorela
            stage.getScene().setOnKeyReleased(
                    event -> keyController.handleKeyReleased(event)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
