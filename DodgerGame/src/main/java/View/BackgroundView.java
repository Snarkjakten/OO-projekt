package View;

import Interfaces.IPlayingFieldObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Olle Westerlund
 */
public class BackgroundView implements IPlayingFieldObserver {
    private GraphicsContext gc;
    private ArrayList<Image> backgroundImages;

    public BackgroundView(GraphicsContext gc) {
        this.gc = gc;
        this.backgroundImages = new ArrayList<>();
        initImages();
    }

    private void initImages() {
        backgroundImages.add(addBackgroundImage());
    }

    private Image addBackgroundImage() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
        Image image = new Image(inputStream);
        return image;
    }

    public void drawBackground(double x, double y, double height, double width, int backgroundNr) {
        Image image = backgroundImages.get(backgroundNr);
        gc.drawImage(image, x, y, width, height);
    }

    @Override
    public void actOnEvent(double width, double height) {
        drawBackground(0,0, height, width, 0);
    }
}
