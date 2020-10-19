package View;

import Model.Entities.Player.Spaceship;
import Model.Entities.Projectiles.*;
import Interfaces.IGameObjectObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @author Olle Westerlund
 */

public class GameObjectGUI implements IGameObjectObserver {
    private GraphicsContext gc;
    private Image image;
    private String spaceshipImageName;
    private LaserGUI laserGUI;
    private double imageWidth;
    private double imageHeight;

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
        if(spaceshipChoice == 1){
            spaceshipImageName = firstChoice;
        } else if(spaceshipChoice == 2) {
            spaceshipImageName = secondChoice;
        } else if(spaceshipChoice == 3) {
            spaceshipImageName = thirdChoice;
        } else {
            spaceshipImageName = fourthChoice;
        }
    }

    /**
     * The method sets the correct image depending on the specific gameObject.
     * @param gameObject The game object to set the image to.
     * @return The a specific image depending on the gameObject.
     * @author Olle Westerlund
     */
    private Image addImageToProjectile(Class gameObject) {
        InputStream inputStream;
        imageWidth = 64;
        imageHeight = 64;
        if (gameObject.equals(Asteroid.class)) {
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
        } else if (gameObject.equals(SlowDebuff.class)) {
            imageWidth = 32;
            imageHeight = 32;
            inputStream = getClass().getClassLoader().getResourceAsStream(timeDebuffPath);
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(LaserBeam.class)) {
            image = laserGUI.getImage();
            imageWidth = image.getWidth();
            imageHeight = image.getHeight();
        }
        return image;
    }

    private void drawImage(double x, double y, Class c, double height, double width) {
        Image image = addImageToProjectile(c);
        gc.drawImage(image, x, y, imageWidth, imageHeight);
    }

    @Override
    public void actOnEvent(double x, double y, Class c, double height, double width) {
        drawImage(x, y, c, height, width);
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
