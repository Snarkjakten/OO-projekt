package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @Author Isak almeros
 */

public class TimeView implements TimeObserver {
    private GraphicsContext gc;

    public TimeView(GraphicsContext gc){
        this.gc = gc;
    }

    private void drawImage(int time){
        Font font = Font.font("Arial", 50);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf(time), 10, 590);
    }

    public void actOnEvent(int time) {
        drawImage(time);
    }
}
