package View;

import Entities.Projectiles.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @Author Olle Westerlund
 */

public class ProjectileGUI implements IObserver {
    GraphicsContext gc;
    private Image image;

    public ProjectileGUI(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * @Author Olle Westerlund
     * The method sets the correct image depending on the specific projectile.
     * @param projectile The projectile to set the image to.
     * @return The a specific image depending on the projectile.
     */
    private Image addImageToProjectile(Class projectile) {
        if (projectile.equals(SmallAsteroid.class)) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("smallAsteroid.png");
            image = new Image(inputStream);
        } else if (projectile.equals(MediumAsteroid.class)) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mediumAsteroid.png");
            image = new Image(inputStream);
        } else if (projectile.equals(HealthPowerUp.class)) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("repair.png");
            image = new Image(inputStream);
        } else if (projectile.equals(ShieldPowerUp.class)) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("shieldPowerUp.png");
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
