package Projectiles;

import javafx.scene.image.Image;
import java.awt.geom.Point2D;
import java.io.InputStream;

/**
 * @Author Olle Westerlund
 */

public class ProjectileGUI {
    private Projectile projectile;
    private Point2D.Double point = new Point2D.Double();
    private Image image;

    public ProjectileGUI(Projectile projectile, double x, double y) {
        this.projectile = projectile;
        this.image = addImageToProjectile(projectile);
        this.point.x = x;
        this.point.y = y;
//        projectile.setxPos(x); Do I need these if so add the set methods back in the projectile class.
//        projectile.setyPos(y);
    }

    //TODO: Decide if return should be Image or BufferedImage and fix method.
    public Image addImageToProjectile(Projectile projectile) {
        if (projectile instanceof SmallAsteroid) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("smallAsteroid.png");
            image = new Image(inputStream);
        } else if (projectile instanceof LargeAsteroid) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mediumAsteroid.png");
            image = new Image(inputStream);
        }
        return image;
    }

    public Projectile getProjectile() {
        return this.projectile;
    }

    public Point2D.Double getPoint() {
        return this.point;
    }

    public Image getImage() {
        return this.image;
    }
}
