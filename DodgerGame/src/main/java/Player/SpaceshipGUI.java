package Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpaceshipGUI {
    private Spaceship spaceship;
    private Point point = new Point();
    private BufferedImage image;

    public SpaceshipGUI(Spaceship spaceship, int x, int y) {
        this.spaceship = spaceship;
        image = addImageToSpaceship(spaceship);
        spaceship.setxPosition(x);
        spaceship.setyPosition(y);
        this.point.x = x;
        this.point.y = y;
    }

    public BufferedImage addImageToSpaceship(Spaceship spaceship) {
        try {
            image = ImageIO.read(Window.class.getResourceAsStream("resources/Spaceship.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public Point getPoint() {
        return point;
    }

    public BufferedImage getImage() {
        return image;
    }
}
