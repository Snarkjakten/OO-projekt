import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

//@Author Viktor Sundberg (viktor.sundberg@icloud.com)

public class HealthBar extends Pane {

    Rectangle hpBackground;
    Rectangle hpBar;

    public HealthBar() {

        //Size of rectangles
        double height = 40;
        double backgroundWidth = 200;
        double hpWidth = 200;

        //Coordinates for rectangles bar in Pane
        double x= 10;
        double y= 750;

        //Assign appearance to rectangles
        hpBackground = new Rectangle( x, y, backgroundWidth, height);
        hpBackground.setStroke(Color.BLACK);
        hpBackground.setStrokeWidth(2);
        hpBackground.setStrokeType( StrokeType.OUTSIDE);
        hpBackground.setFill(Color.RED);

        hpBar = new Rectangle( x, y, hpWidth, height);
        hpBar.setStroke(Color.BLACK);
        hpBar.setStrokeWidth(1);
        hpBar.setStrokeType( StrokeType.OUTSIDE);
        hpBar.setFill(Color.GREEN);

        //Add rectangles to healthbar
        getChildren().addAll(hpBackground, hpBar);

    }

    //Read current HP
    double getHp(double hp) {
        double currentHp = hpBar.getWidth();
        return currentHp;
    }

    //Heal/take damage using this
    double setHp(double healthDif) {
        hpBar.setWidth((hpBar.getWidth() + (healthDif)));
        return healthDif;
    }

}
