import Entities.Projectiles.Projectile;
import Entities.Projectiles.ProjectileFactory;
import Entities.Projectiles.ProjectileGUI;
import Entities.Ship;
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

    // --- todo: flytta main från Window --------------------
    public static void main(String[] args) {
        launch(args);
    }
    Ship ship = new Ship();
    ProjectileGUI projectileGUI = new ProjectileGUI(ProjectileFactory.createMediumAsteroid(), 100, 100);
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
            ImageView iV = new ImageView(windowBackground);
            iV.setImage(windowBackground);

            ImageView testAsteroid = new ImageView(projectileGUI.getImage());
            System.out.println(projectileGUI.getProjectile().getDirection());
            System.out.println(projectileGUI.getProjectile().getSpeed());
            System.out.println(projectileGUI.getProjectile().getVertical());
            System.out.println(projectileGUI.getProjectile().getHorizontal());


            //Sets image size to fit Pane size (hard coded for now)
            iV.setFitHeight(600);
            iV.setFitWidth(800);

            //Adds ImageView to Pane
            win.getChildren().addAll(iV, testAsteroid);

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

            int i = 0;
            while (i < 20) {
                projectileGUI.getProjectile().move();
                Projectile projectile = projectileGUI.getProjectile();
                projectileGUI = new ProjectileGUI(projectile, projectile.getHorizontal(), projectile.getVertical());
                testAsteroid.setImage(projectileGUI.getImage());

                System.out.println(projectileGUI.getPoint().getX());
                System.out.println(projectileGUI.getPoint().getY());
                i++;
            }
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
