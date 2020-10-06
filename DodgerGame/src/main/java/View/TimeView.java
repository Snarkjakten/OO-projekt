package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @Author Isak almeros
 */

public class TimeView implements TimeObserver {
    private GraphicsContext gc;
    private StringBuilder sb = new StringBuilder("00:00");

    public TimeView(GraphicsContext gc){
        this.gc = gc;

        Font font = Font.font("Arial", 50);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
    }

    private void drawImage(int seconds){
        int s = seconds % 60;
        int m = seconds / 60;

        if(m < 10 && s < 10) {
            sb.replace(0, sb.capacity(), "0" + m + ":" + "0" + s);
        } else if (m < 10 && s >= 10) {
            sb.replace(0, sb.capacity(), "0" + m + ":" + s);
        } else if (m >= 10 && s < 10) {
            sb.replace(0, sb.capacity(),  m + ":" + "0" + s);
        } else  {
            sb.replace(0, sb.capacity(),  m + ":" + s);
        }

        gc.fillText(sb.toString(), 20, 590);
    }

    public void actOnEvent(int time) {
        drawImage(time);
    }
}
