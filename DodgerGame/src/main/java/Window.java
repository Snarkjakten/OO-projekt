import Entities.TestShip;
import Entities.TestProjectile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window extends Application {


    // --------------------------------------
    // todo: flytta ut från window
    // main, spelare och projektil för test av move
    public static void main(String[] args) {
        launch(args);
    }

    TestShip ship = new TestShip();
    TestProjectile asteroid = new TestProjectile();
    // --------------------------------------

    //Creates Pane
    private final Pane win = new Pane();
    //Gets image from resources
    Image windowBackground = new Image("file:src/main/resources/space.jpg");

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
            iV.setImage(windowBackground);

            //Sets image size to fit Pane size (hard coded for now)
            iV.setFitHeight(600);
            iV.setFitWidth(800);

            //Adds ImageView to Pane
            win.getChildren().addAll(iV);

            //Sets scene from created Pane createContent
            stage.setScene(new Scene(createContent()));
            //Removes option to change size of progam window
            stage.setResizable(false);
            //Opens program window
            stage.show();


            // ------------------------------------------------------------
            // todo: refactor and move
            // When an arrow key is pressed, the ship moves in that direction
            // @author Irja Vuorela
            stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            ship.up = 1;
                            ship.move();
                            asteroid.move();
                            break;
                        case DOWN:
                            ship.down = 1;
                            ship.move();
                            asteroid.move();
                            break;
                        case LEFT:
                            ship.left = 1;
                            ship.move();
                            asteroid.move();
                            break;
                        case RIGHT:
                            ship.right = 1;
                            ship.move();
                            asteroid.move();
                            break;
                        default:
                            break;
                    }
                }
            });
            // When an arrow key is released, the ship stops moving in that direction
            stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            ship.up = 0;
                            break;
                        case DOWN:
                            ship.down = 0;
                            break;
                        case LEFT:
                            ship.left = 0;
                            break;
                        case RIGHT:
                            ship.right = 0;
                            break;
                        default:
                            break;
                    }
                }
            });
            // ------------------------------------------------------------------------


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
