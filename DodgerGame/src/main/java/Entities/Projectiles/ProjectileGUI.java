package Entities.Projectiles;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import java.io.InputStream;

/**
 * @Author Olle Westerlund
 */

public class ProjectileGUI {
    private Projectile projectile;
    private Point2D point;
    private Image image;
    private double xPos;
    private double yPos;

    public ProjectileGUI(Projectile projectile) {
        this.projectile = projectile;
        this.xPos = projectile.position.getX();
        this.yPos = projectile.position.getY();
        this.point = new Point2D(xPos, yPos);
        this.image = addImageToProjectile(projectile);
    }

    /**
     * @Author Olle Westerlund
     * @param projectile The projectile to set the image to.
     * @return The a specific image depending on the projectile.
     */
    public Image addImageToProjectile(Projectile projectile) {
        if (projectile instanceof SmallAsteroid) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("smallAsteroid.png");
            image = new Image(inputStream);
        } else if (projectile instanceof MediumAsteroid) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mediumAsteroid.png");
            image = new Image(inputStream);
        } else if (projectile instanceof HealthPowerUp) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("repair.png");
            image = new Image(inputStream);
        } else if (projectile instanceof ShieldPowerUp) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("shieldPowerUp.png");
            image = new Image(inputStream);
        }
        return image;
    }

    public Projectile getProjectile() {
        return this.projectile;
    }

    public Point2D getPoint() {
        return point;
    }

    public Image getImage() {
        return this.image;
    }

    public double getHorizontalPosition() {
        return projectile.position.getX();
    }

    public double getVerticalPosition() {
        return projectile.position.getY();
    }
}
