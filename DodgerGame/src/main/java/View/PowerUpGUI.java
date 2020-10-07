package View;

import Entities.Player.Player;
import Entities.Player.Spaceship;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

public class PowerUpGUI {
    private GraphicsContext gc;
    private Image[] frames;
    private double duration;

    public PowerUpGUI(GraphicsContext gc, double duration) {
        this.gc = gc;
        this.duration = duration;
        this.frames = new Image[20];
        initImages();

    }

    private void initImages() {
        for (int i = 0; i < frames.length; i++) {
            frames[i] = setImage(i);
        }
    }

    private Image setImage(int number) {
        InputStream inputStream;
        Image image;
        String url = "shield/shield" + number + ".png";
        inputStream = getClass().getClassLoader().getResourceAsStream(url);
        image = new Image(inputStream);
        return image;
    }

    private Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void drawImage(Player player, double animationTime) {
        if (player.getNrOfShields() > 0) {
            Image image = getFrame(animationTime);
            for (Spaceship ship : player.getSpaceships()) {
                double xPos = ship.position.getX() - 7;
                double yPos = ship.position.getY() - 7;
                double height = ship.getHeight() * 1.25;
                double width = ship.getWidth() * 1.25;
                gc.drawImage(image, xPos, yPos, height, width);
            }
        }
    }
}
