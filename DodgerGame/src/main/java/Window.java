import Projectiles.ProjectileFactory;
import Projectiles.ProjectileGUI;
import javafx.application.Application;
import javafx.scene.Scene;
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
    private ProjectileGUI smallAsteroid = new ProjectileGUI(ProjectileFactory.createSmallAsteroid(), 400,400);
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
    Image windowBackground = new Image(inputStream);

//    Image windowBackground = new Image("file:/src/main/resources/space.jpg");



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
            ImageView testAsteroid = new ImageView(smallAsteroid.getImage());
            iV.setImage(windowBackground);

            //Sets image size to fit Pane size (hard coded for now)
            iV.setFitHeight(800);
            iV.setFitWidth(1200);

            //Adds ImageView to Pane
            win.getChildren().addAll(iV, testAsteroid);
            testAsteroid.setX(testAsteroid.getX());
            testAsteroid.setY(testAsteroid.getY());

            //Sets scene from created Pane createContent
            stage.setScene(new Scene(createContent()));
            //Removes option to change size of progam window
            stage.setResizable(false);
            //Opens program window
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
