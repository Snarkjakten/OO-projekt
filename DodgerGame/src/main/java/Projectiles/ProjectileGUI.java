package Projectiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
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
        projectile.setxPos(x);
        projectile.setyPos(y);
    }

    //TODO: Decide if return should be Image or BufferedImage and fix method.
    public Image addImageToProjectile(Projectile projectile) {
        try {
            if (projectile instanceof SmallAsteroid) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("smallAsteroid.png");
                image = ImageIO.read(inputStream);
//                image = ImageIO.read(Window.class.getResourceAsStream("resources/smallAsteroid.png"));
            } else if (projectile instanceof LargeAsteroid) {
                ImageIO.read(Window.class.getResourceAsStream("resources/mediumAsteroid.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
