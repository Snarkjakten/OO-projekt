package Projectiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectileGUI {
    private Projectile projectile;
    private Point point;
    private BufferedImage image;

    public ProjectileGUI(Projectile projectile, int x, int y) {
        this.projectile = projectile;
//        this.image = addImageToProjectile(projectile);
        this.point.x = x;
        this.point.y = y;
        projectile.setxPos(x);
        projectile.setyPos(y);
    }

//    private BufferedImage addImageToProjectile(Projectile projectile) {
//        try {
//            if (projectile instanceof SmallAsteroid) {
//                image = ImageIO.read(DrawPanel.class.getResourceAsStream("resources/smallAsteroid.png"));
//            } else if (projectile instanceof LargeAsteroid) {
//                ImageIO.read(DrawPanel.class.getResourceAsStream("resources/mediumAsteroid.png"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }

    public Projectile getProjectile() {
        return this.projectile;
    }

    public Point getPoint() {
        return this.point;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
