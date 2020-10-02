package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TimeView {
    private GraphicsContext gc;

    public TimeView(GraphicsContext gc){
        this.gc = gc;
    }

    public void drawImage(int time){
        Font font = Font.font("Arial", 50);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf(time), 10, 590);
    }
}
