package Entities.Player;

import javafx.geometry.Point2D;

import java.io.InputStream;

import javafx.scene.image.Image;

public class SpaceshipGUI {
    private Spaceship spaceship;
    private Point2D point;
    private Image image;

    //@Author Tobias Engblom
    public SpaceshipGUI(Spaceship spaceship, double x, double y) {
        this.spaceship = spaceship;
        this.image = addImageToSpaceship();
        spaceship.setPosition(x, y);
        point = new Point2D(x, y);
    }

    //@Author Tobias Engblom
    public Image addImageToSpaceship() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("spaceship.gif");
        image = new Image(inputStream);
        return image;
    }

    //@Author Tobias Engblom
    public Spaceship getSpaceship() {
        return spaceship;
    }

    //@Author Tobias Engblom
    public void setPosition(double xPos, double yPos) {
        this.point = point.add(xPos, yPos);
        spaceship.setPosition(xPos, yPos);
    }

    //@Author Tobias Engblom
    public double getXPosition() {
        return spaceship.position.getX();
    }

    //@Author Tobias Engblom
    public double getYPosition() {
        return spaceship.position.getY();
    }

    //@Author Tobias Engblom
    public Point2D getPoint() {
        return point;
    }

    //@Author Tobias Engblom
    public Image getImage() {
        return image;
    }
}
