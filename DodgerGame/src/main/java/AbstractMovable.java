import javafx.geometry.Point2D;

/*
 * @Author Irja
 */

public abstract class AbstractMovable {

    // todo: position kan brytas ut till en egen abstrakt klass om vi vill ha något som inte rör sig
    // Position in x- and y-coordinates
    private Point2D position = new Point2D(0, 0);

    // Velocity (direction + speed)
    private Point2D velocity = new Point2D(0, 0);

    // Movement speed
    private final double speed = 10;
    // Movement directions todo: make enum
    public int up = 0;
    public int down = 0;
    public int left = 0;
    public int right = 0;

    // move to a new position todo: normalize vector (or else diagonal movement will take you further)
    public void move() {
        // update position
        this.position = position.add(velocity.getX(), velocity.getY()); // add() returns a new point2D
        // update velocity (direction + speed)
        // stop if moving in two opposite directions simultaneously
        this.velocity = new Point2D(((right - left) * speed), ((up - down) * speed));

        // todo: remove print later
        System.out.println("Moved to (" + position.getX() + ", " + position.getY() + ")");
    }
}
