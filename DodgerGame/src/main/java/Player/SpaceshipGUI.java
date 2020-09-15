package Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;

public class SpaceshipGUI {
    private Spaceship spaceship;
    private Point2D.Double point = new Point2D.Double();
    private Image image;

    public SpaceshipGUI(Spaceship spaceship, double x, double y) {
        this.spaceship = spaceship;
        this.image = addImageToSpaceship(spaceship);
        spaceship.setxPosition(x);
        spaceship.setyPosition(y);
        this.point.x = x;
        this.point.y = y;
    }

    public Image addImageToSpaceship(Spaceship spaceship) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("spaceship.gif");
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public Point2D.Double getPoint() {
        return point;
    }

    public Image getImage() {
        return image;
    }
}
