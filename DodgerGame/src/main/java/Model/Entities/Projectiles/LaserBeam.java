package Model.Entities.Projectiles;

import Model.Entities.HitBox;
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
    private final int damage;

    private final double horizontalMapSize = GameWorld.getInstance().getPlayingFieldWidth();
    private final double verticalMapSize = GameWorld.getInstance().getPlayingFieldHeight();


    public LaserBeam() {
        setSpeed(100);
        this.damage = 100;
        getHitBoxes().add(new HitBox(0, 0, 1, 1));
        randomStartPoint();
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    public void updateVelocity() {
        this.velocity = (new Point2D(horizontal, vertical).normalize());
        this.velocity.multiply(getSpeed());
    }

    /**
     * @author Olle Westerlund
     * The method sets a starting side and then sets the position the laser beam
     * moves towards.
     */
    private void randomStartPoint() {
        HitBox hitBox = getHitBoxes().get(0);
        Random random = new Random();
        int side = random.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                this.isVertical = false;
                setStopPosition(0, -50);
                hitBox.updatePosition(-50, verticalMapSize + 50);
                break;
            case 1: // Right side of the screen
                this.isVertical = true;
                setStopPosition(-50, 0);
                hitBox.updatePosition(horizontalMapSize + 50, -50);
                break;
            case 2: // Top of the screen
                this.isVertical = false;
                setStopPosition(0, verticalMapSize);
                hitBox.updatePosition(-50, -50);
                break;
            case 3: // Left of the screen
                this.isVertical = true;
                setStopPosition(horizontalMapSize, 0);
                hitBox.updatePosition(-50, -50);
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
