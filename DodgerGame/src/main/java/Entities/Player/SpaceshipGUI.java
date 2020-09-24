package Entities.Player;

import javafx.geometry.Point2D;

import java.io.InputStream;

import javafx.scene.image.Image;

public class SpaceshipGUI {
    private Spaceship spaceship;
    private Point2D point;
    private Image image;

    /**
     * @Author Tobias Engblom
     */
    public SpaceshipGUI(Spaceship spaceship, double x, double y) {
        this.spaceship = spaceship;
        this.image = addImageToSpaceship();
        spaceship.setPosition(x, y);
        point = new Point2D(x, y);
    }

    public Image addImageToSpaceship() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("spaceship.gif");
        image = new Image(inputStream);
        return image;
    }

    protected Spaceship getSpaceship() {
        return spaceship;
    }

    public void setPosition(double xPos, double yPos) {
        this.point = point.add(xPos, yPos);
        spaceship.setPosition(xPos, yPos);
    }

    public double getXPosition() {
        return spaceship.position.getX();
    }

    public double getYPosition() {
        return spaceship.position.getY();
    }

    public Point2D getPoint() {
        return point;
    }

    public Image getImage() {
        return image;
    }
    //------------------------------------------------------------------------------------------------------------------
}
