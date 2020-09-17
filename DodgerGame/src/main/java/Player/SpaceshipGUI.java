package Player;

import javafx.geometry.Point2D;
import java.io.InputStream;

import javafx.scene.image.Image;

public class SpaceshipGUI {
    private Spaceship spaceship;
    private Point2D point = new Point2D(0, 0);
    private Image image;

    public SpaceshipGUI(Spaceship spaceship, double x, double y) {
        this.spaceship = spaceship;
        this.image = addImageToSpaceship(spaceship);
        spaceship.setxPosition(x);
        spaceship.setyPosition(y);
        this.point.add(x, y);
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

    protected Point2D getPoint() {
        return point;
    }

    public Image getImage() {
        return image;
    }
}
