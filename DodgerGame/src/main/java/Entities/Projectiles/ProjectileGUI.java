package Entities.Projectiles;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import java.io.InputStream;

/**
 * @Author Olle Westerlund
 */

public class ProjectileGUI {
    private Projectile projectile;
    private javafx.geometry.Point2D point;
    private Image image;

    public ProjectileGUI(Projectile projectile, double x, double y) {
        this.point = new Point2D(x, y);
        this.projectile = projectile;
        this.image = addImageToProjectile(projectile);
        this.point.add(x, y);
    }

    public Image addImageToProjectile(Projectile projectile) {
        if (projectile instanceof SmallAsteroid) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("smallAsteroid.png");
            image = new Image(inputStream);
        } else if (projectile instanceof MediumAsteroid) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mediumAsteroid.png");
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
}
