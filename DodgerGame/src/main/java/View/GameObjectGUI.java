package View;

import Entities.Player.Spaceship;
import Entities.Projectiles.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @Author Olle Westerlund
 */

public class GameObjectGUI implements IObserver {
    GraphicsContext gc;
    private Image image;
    private final String spaceshipImageName;

    public GameObjectGUI(GraphicsContext gc, String spaceshipImageName) {
        this.gc = gc;
        this.spaceshipImageName = spaceshipImageName;
    }

    /**
     * @param gameObject The gameObject to set the image to.
     * @return The a specific image depending on the gameObject.
     * @Author Olle Westerlund
     * The method sets the correct image depending on the specific gameObject.
     */
    private Image addImageToProjectile(Class gameObject) {
        InputStream inputStream;
        if (gameObject.equals(SmallAsteroid.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream("smallAsteroid.png");
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(MediumAsteroid.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream("mediumAsteroid.png");
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(HealthPowerUp.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream("repair.png");
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(ShieldPowerUp.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream("shieldPowerUp.png");
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(Spaceship.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream(spaceshipImageName);
            assert inputStream != null;
            image = new Image(inputStream);
        } else if (gameObject.equals(Debuff.class)) {
            inputStream = getClass().getClassLoader().getResourceAsStream("skull.png");
            assert inputStream != null;
            image = new Image(inputStream);
        }
        return image;
    }

    private void drawImage(double x, double y, Class c, double height, double width) {
        Image image = addImageToProjectile(c);
        gc.drawImage(image, x, y, width, height);
    }

    @Override
    public void actOnEvent(double x, double y, Class c, double height, double width) {
        drawImage(x, y, c, height, width);
    }
}
