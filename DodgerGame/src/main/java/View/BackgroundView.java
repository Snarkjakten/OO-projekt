package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
//TODO: Fix so this class draws the background
public class BackgroundView {
    GraphicsContext gc;
    Image image;

    public BackgroundView(GraphicsContext gc) {
        this.gc = gc;
    }

    private Image addImageToBackground() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
        image = new Image(inputStream);
        return image;
    }

    private void drawBackground(double x, double y, double height, double width) {
        Image image = addImageToBackground();
        gc.drawImage(image, x, y, width, height);
        System.out.println("Bg addImage");
    }

}
