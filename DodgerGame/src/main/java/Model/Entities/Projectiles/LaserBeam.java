package Model.Entities.Projectiles;

import Model.GameWorld;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class LaserBeam extends Projectile {
    private double horizontal;
    private double vertical;
    private boolean isVertical;
    private final int damage = 100;
    private final double horizontalMapSize = GameWorld.getInstance().getPlayingFieldWidth();
    private final double verticalMapSize = GameWorld.getInstance().getPlayingFieldHeight();

    public LaserBeam() {
        super(100);
        randomStartPoint();
        initSize();
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    private void initSize() {
        if (isVertical) {
            this.width = 256;
            this.height = verticalMapSize + 100;
        } else {
            this.width = horizontalMapSize + 100;
            this.height = 256;
        }
    }

    private void updateVelocity() {
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        this.velocity = velocity.multiply(this.getSpeed());
    }

    /**
     * @author Olle Westerlund
     * The method sets a starting side and then sets the position the laser beam
     * moves towards.
     */
    private void randomStartPoint() {
        Random random = new Random();
        int side = random.nextInt(2);
        switch (side) {
            case 0: // Bottom of the screen
                this.isVertical = false;
                setStopPosition(0,-50);
                this.position = new Point2D(-50, verticalMapSize + 50);
                break;
            case 1: // Right side of the screen
                this.isVertical = true;
                setStopPosition(-50,0);
                this.position = new Point2D(horizontalMapSize + 50, -50);
                break;
            case 2: // Top of the screen
                this.isVertical = false;
                setStopPosition(0, verticalMapSize);
                this.position = new Point2D(-50, -50);
                break;
            case 3: // Left of the screen
                this.isVertical = true;
                setStopPosition(horizontalMapSize, 0);
                this.position = new Point2D(-50, -50);
                break;
            default:
                System.out.println("Error in randomStartPoint");
                break;
        }
    }

    public void setStopPosition(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isVertical() {
        return isVertical;
    }
}
