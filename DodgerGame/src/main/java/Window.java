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
    // Irjas main och spelare för test av move todo: flytta
    public static void main(String[] args) {
        launch(args);
    }

    Player player = new Player();
    // --------------------------------------

    //Creates Pane
    private final Pane win = new Pane();
    //Gets image from resources
    Image windowBackground = new Image("file:src/main/resources/space.jpg");

    //Sets size of Pane
    private Pane createContent() {
        win.setPrefSize(1200, 800);
        return win;
    }

    @Override
    public void start(Stage stage) {
        try {
            //Creates ImageView and sets image space.jpg as view
            ImageView iV = new ImageView(windowBackground);
            iV.setImage(windowBackground);

            //Sets image size to fit Pane size (hard coded for now)
            iV.setFitHeight(800);
            iV.setFitWidth(1200);

            //Adds ImageView to Pane
            win.getChildren().addAll(iV);

            //Sets scene from created Pane createContent
            stage.setScene(new Scene(createContent()));
            //Removes option to change size of progam window
            stage.setResizable(false);
            //Opens program window
            stage.show();


            // @author Irja  ------------------------------------------------------------
            // todo: refactor
            // When an arrow key is pressed, the player moves in that direction
            stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            player.up = 1;
                            player.move();
                            break;
                        case DOWN:
                            player.down = 1;
                            player.move();
                            break;
                        case LEFT:
                            player.left = 1;
                            player.move();
                            break;
                        case RIGHT:
                            player.right = 1;
                            player.move();
                            break;
                        default:
                            break;
                    }
                }
            });
            // When an arrow key is released, the player stops moving in that direction
            stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            player.up = 0;
                            break;
                        case DOWN:
                            player.down = 0;
                            break;
                        case LEFT:
                            player.left = 0;
                            break;
                        case RIGHT:
                            player.right = 0;
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
