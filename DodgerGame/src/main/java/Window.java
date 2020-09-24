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
import java.util.ArrayList;
import java.util.List;

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
    Spaceship spaceship = SpaceshipFactory.createSpaceship(true);
    Spaceship wrapAroundSpaceship = SpaceshipFactory.createSpaceship(false);
    SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 368, 268);
    SpaceshipGUI wrapAroundSpaceshipGUI = new SpaceshipGUI(wrapAroundSpaceship, 900, 900);
    Image spaceShipImage = spaceshipGUI.getImage();
    List<Spaceship> spaceships = new ArrayList<>();


    //Sets size of Pane
    private Pane createContent() {
        win.setPrefSize(800, 600);
        return win;
    }

    @Override
    public void start(Stage stage) {
        spaceships.add(spaceship);
        spaceships.add(wrapAroundSpaceship);
        try {
            // @Author Tobias Engblom
            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            //Adds ImageView and Canvas to Pane
            win.getChildren().addAll(canvas);

            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    gc.drawImage(windowBackground, 0, 0, 800, 600);
                    gc.drawImage(spaceShipImage, spaceshipGUI.getXPosition(), spaceshipGUI.getYPosition(), 64, 64);
                    gc.drawImage(spaceShipImage, wrapAroundSpaceshipGUI.getXPosition(), wrapAroundSpaceshipGUI.getYPosition(), 64, 64);
                    if (spaceship.isActive() && wrapAroundSpaceship.isActive()) {
                        System.out.println("BOTH ACTIVE");
                        //TODO What happens when both ships are active and crosses the "wall"?
                    } else if (spaceship.isActive()) {
                        switch ((int) spaceshipGUI.getXPosition()) {
                            case -12:
                                wrapAroundSpaceship.setActive(true);
                                wrapAroundSpaceshipGUI.setPosition(788, spaceshipGUI.getYPosition());
                                break;
                            case 724:
                                wrapAroundSpaceship.setActive(true);
                                wrapAroundSpaceshipGUI.setPosition(-76, spaceshipGUI.getYPosition());
                                break;
                            default:
                                break;
                        }
                        switch ((int) spaceshipGUI.getYPosition()) {
                            case 0:
                                wrapAroundSpaceship.setActive(true);
                                wrapAroundSpaceshipGUI.setPosition(spaceshipGUI.getXPosition(), 600);
                                break;
                            case 536:
                                wrapAroundSpaceship.setActive(true);
                                wrapAroundSpaceshipGUI.setPosition(spaceshipGUI.getXPosition(), -64);
                                break;
                            default:
                                break;
                        }
                    } else if (wrapAroundSpaceship.isActive()) {
                        switch ((int) wrapAroundSpaceshipGUI.getXPosition()) {
                            case -12:
                                spaceship.setActive(true);
                                spaceshipGUI.setPosition(788, wrapAroundSpaceshipGUI.getYPosition());
                                break;
                            case 724:
                                spaceship.setActive(true);
                                spaceshipGUI.setPosition(-76, wrapAroundSpaceshipGUI.getYPosition());
                                break;
                            default:
                                break;
                        }
                        switch ((int) wrapAroundSpaceshipGUI.getYPosition()) {
                            case 0:
                                spaceship.setActive(true);
                                spaceshipGUI.setPosition(wrapAroundSpaceshipGUI.getXPosition(), 600);
                                break;
                            case 536:
                                spaceship.setActive(true);
                                spaceshipGUI.setPosition(wrapAroundSpaceshipGUI.getXPosition(), -64);
                                break;
                            default:
                                break;
                        }
                    }
                    if (spaceshipGUI.getXPosition() <= -76 || spaceshipGUI.getXPosition() >= 788 || spaceshipGUI.getYPosition() <= -64 || spaceshipGUI.getYPosition() >= 600)
                        spaceship.setActive(false);
                    else if (wrapAroundSpaceshipGUI.getXPosition() <= -76 || wrapAroundSpaceshipGUI.getXPosition() >= 788 || wrapAroundSpaceshipGUI.getYPosition() <= -64 || wrapAroundSpaceshipGUI.getYPosition() >= 600)
                        wrapAroundSpaceship.setActive(false);
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
            KeyController keyController = new KeyController(spaceships);
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
}
