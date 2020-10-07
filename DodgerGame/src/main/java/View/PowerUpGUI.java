package View;

import Entities.Player.Player;
import Entities.Player.Spaceship;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

public class PowerUpGUI {
    GraphicsContext gc;
    Image image;

    public PowerUpGUI(GraphicsContext gc) {
        this.gc = gc;
    }

    private Image setImage(Player player) {
        InputStream inputStream;
        inputStream = getClass().getClassLoader().getResourceAsStream("shield.png");
        assert inputStream != null;
        image = new Image(inputStream);
        return image;
    }

    public void drawImage(Player player) {
        if (player.getNrOfShields() > 0) {
            Image image = setImage(player);
            for (Spaceship ship : player.getSpaceships()) {
                double xPos = ship.position.getX();
                double yPos = ship.position.getY();
                double height = ship.getHeight() * 1.25;
                double width = ship.getWidth() * 1.25;
                gc.drawImage(image, xPos, yPos, height, width);
            }
        }
    }
}
