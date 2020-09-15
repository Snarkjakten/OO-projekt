package Player;

import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;

import javafx.scene.image.Image;

import java.io.IOException;

public class SpaceshipGUI {
    private Spaceship spaceship;
    private Point point = new Point();
    private ImageView image;

    public SpaceshipGUI(Spaceship spaceship, int x, int y) {
        this.spaceship = spaceship;
        image = addImageToSpaceship(spaceship);
        spaceship.setxPosition(x);
        spaceship.setyPosition(y);
        this.point.x = x;
        this.point.y = y;
    }

    public ImageView addImageToSpaceship(Spaceship spaceship) {
        Image image = new Image("file:src/main/resources/spaceship.gif");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public Point getPoint() {
        return point;
    }

    public ImageView getImage() {
        return image;
    }
}
