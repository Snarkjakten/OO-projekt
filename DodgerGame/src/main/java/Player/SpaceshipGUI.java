package Player;

import java.awt.geom.Point2D;
import java.io.InputStream;

import javafx.scene.image.Image;

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
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("spaceship.gif");
        image = new Image(inputStream);
        return image;
    }

    protected Spaceship getSpaceship() {
        return spaceship;
    }

    public double getxPosition() {
        return spaceship.getxPosition();
    }

    public double getYPosition() {
        return spaceship.getyPosition();
    }

    protected Point2D.Double getPoint() {
        return point;
    }

    public Image getImage() {
        return image;
    }
}
