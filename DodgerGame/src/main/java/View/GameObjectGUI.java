package View;

import Model.Entities.HitBox;
import Model.Entities.Player.Spaceship;
import Model.Entities.Projectiles.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.List;

/**
 * @author Olle Westerlund
 */

public class GameObjectGUI {
    private final GraphicsContext gc;
    private Image image;
    private String spaceshipImageName;
    private final LaserGUI laserGUI;
    private double imageWidth;
    private double imageHeight;
    private double deltaTime;

    private static final String firstChoice = "thor.gif";
    private static final String secondChoice = "turtle.png";
    private static final String thirdChoice = "ufo.gif";
    private static final String fourthChoice = "lighter.gif";
    private static final String smallAsteroidFilePath = "smallAsteroid.png";
    private static final String mediumAsteroidFilePath = "mediumAsteroid.png";
    private static final String shieldFilePath = "healthPowerUp.png";
    private static final String hpFilePath = "shieldPowerUp.png";
    private static final String timeDebuffPath = "skull.png";

    public GameObjectGUI(GraphicsContext gc) {
        this.gc = gc;
        this.laserGUI = LaserGUI.getInstance();
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
     * The method sets the correct image depending on the specific gameObject.
     *
     * @param gameObject The gameObject to set the image to.
     * @return The a specific image depending on the gameObject.
     * @author Olle Westerlund
     */
    private Image addImageToGameObject(Class gameObject, double width, double height) {
        InputStream inputStream;
        imageWidth = 64;
        imageHeight = 64;
        if (gameObject.equals(Asteroid.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream(mediumAsteroidFilePath);
            assert inputStream != null;
            image = new Image(inputStream);
            if (width > 32) {
                imageWidth = 100;
                imageHeight = 100;
            }
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
        } else if (gameObject.equals(SlowDebuff.class)) {
            imageWidth = 32;
            imageHeight = 32;
            inputStream = getClass().getClassLoader().getResourceAsStream(timeDebuffPath);
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(LaserBeam.class)) {
            laserGUI.setIsVertical(!(width > height));
            image = laserGUI.getFrame(deltaTime);
            imageWidth = image.getWidth();
            imageHeight = image.getHeight();
        }
        return image;
    }

    /**
     * Draws the correct image on the current position for the object
     *
     * @param hitBoxes  The hitboxes of the object to be drawn.
     * @param c         The class of the object
     * @param width     The objects width
     * @param height    The objects height
     * @param deltaTime
     * @author Olle Westerlund
     */
    public void drawImage(List<HitBox> hitBoxes, Class c, double width, double height, double deltaTime) {
        this.deltaTime = deltaTime;
        Image image = addImageToGameObject(c, width, height);
        for (HitBox hitBox : hitBoxes) {
            if (c.equals(LaserBeam.class)) {
                if (imageWidth > imageHeight) {
                    gc.drawImage(image, (hitBox.getX()), (hitBox.getY()) - (256 / 2), imageWidth, imageHeight);
                } else {
                    gc.drawImage(image, hitBox.getX() - (257 / 2), (hitBox.getY()), imageWidth, imageHeight);
                }
            } else {
                gc.drawImage(image, (hitBox.getX() - imageWidth / 2), (hitBox.getY() - imageHeight / 2), imageWidth, imageHeight);
            }
        }
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
