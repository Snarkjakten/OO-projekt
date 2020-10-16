package View;

import Game.Entities.Player.HitBox;
import Game.Entities.Player.Spaceship;
import Game.Entities.Projectiles.*;
import Interfaces.IGameObjectObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.List;

/**
 * @author Olle Westerlund
 */

public class GameObjectGUI implements IGameObjectObserver {
    private final GraphicsContext gc;
    private Image image;
    private String spaceshipImageName;

    private static final String firstChoice = "thor.gif";
    private static final String secondChoice = "turtle.png";
    private static final String thirdChoice = "ufo.gif";
    private static final String fourthChoice = "lighter.gif";
    private static final String smallAsteroidFilePath = "smallAsteroid.png";
    private static final String mediumAsteroidFilePath = "mediumAsteroid.png";
    private static final String shieldFilePath = "healthPowerUp.png";
    private static final String hpFilePath = "shieldPowerUp.png";

    public GameObjectGUI(GraphicsContext gc) {
        this.gc = gc;
    }

    public void chooseSpaceshipImage(int spaceshipChoice) {
        if (spaceshipChoice == 1) {
            spaceshipImageName = firstChoice;
        } else if (spaceshipChoice == 2) {
            spaceshipImageName = secondChoice;
        } else if (spaceshipChoice == 3) {
            spaceshipImageName = thirdChoice;
        } else {
            spaceshipImageName = fourthChoice;
        }
    }

    /**
     * @param gameObject The gameObject to set the image to.
     * @return The a specific image depending on the gameObject.
     * @author Olle Westerlund
     * The method sets the correct image depending on the specific gameObject.
     */
    private Image addImageToGameObject(Class gameObject) {
        InputStream inputStream;
        if (gameObject.equals(SmallAsteroid.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream(smallAsteroidFilePath);
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(MediumAsteroid.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream(mediumAsteroidFilePath);
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(HealthPowerUp.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream(shieldFilePath);
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(ShieldPowerUp.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream(hpFilePath);
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(Spaceship.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream(spaceshipImageName);
            assert inputStream != null;
            image = new Image(inputStream);
        }
        return image;
    }

    private void drawImage(List<HitBox> hitBoxes, Class c, double height, double width) {
        Image image = addImageToGameObject(c);
        for (HitBox hitBox : hitBoxes)
            gc.drawImage(image, hitBox.getXPos(), hitBox.getYPos(), width, height);
    }

    @Override
    public void actOnEvent(List<HitBox> hitBoxes, Class c, double height, double width) {
        drawImage(hitBoxes, c, height, width);
    }

    public static String getFirstChoice() {
        return firstChoice;
    }

    public static String getSecondChoice() {
        return secondChoice;
    }

    public static String getThirdChoice() {
        return thirdChoice;
    }

    public static String getFourthChoice() {
        return fourthChoice;
    }
}
