import Player.SpaceshipFactory;
import Player.SpaceshipGUI;
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

    //Creates Pane
    private final Pane win = new Pane();
    //Gets image from resources
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);
    SpaceshipGUI spaceshipGUI = new SpaceshipGUI(SpaceshipFactory.createSpaceship(), 600, 400);
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
            Image spaceShipImage = spaceshipGUI.getImage();
            Canvas canvas = new Canvas(1200, 800);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(spaceShipImage, spaceshipGUI.getxPosition(), spaceshipGUI.getYPosition());

            //Sets image size to fit Pane size (hard coded for now)
            iV.setFitHeight(800);
            iV.setFitWidth(1200);

            //Adds ImageView to Pane
            win.getChildren().addAll(iV, canvas);

            //Sets scene from created Pane createContent
            stage.setScene(new Scene(createContent()));
            //Removes option to change size of program window
            stage.setResizable(false);
            //Opens program window
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
