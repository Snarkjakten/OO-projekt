package Model.Entities.Projectiles;

import Model.GameWorld;
import Model.Movement.AbstractGameObject;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class LaserBeam extends AbstractGameObject {
    private double horizontal;
    private double vertical;
    private boolean isVertical;
    private int damage;
    private double horizontalMapSize = GameWorld.getInstance().getPlayingFieldWidth();
    private double verticalMapSize = GameWorld.getInstance().getPlayingFieldHeight();

    public LaserBeam() {
        this.setSpeed(100);
        this.damage = 100;
        randomStartPoint();
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
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
        int side = random.nextInt(4);
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
}