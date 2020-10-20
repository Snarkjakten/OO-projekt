package Model.Entities.Projectiles;

import Model.Entities.HitBox;
import Model.GameWorld;
import Model.Point2D;

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
        super(100, 1, 1);
        randomStartPoint();
        initSize();
    }

    public LaserBeam(int side) {
        super(100, 1, 1);
        moveDirection(side);
        initSize();
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    private void initSize() {
        if (isVertical) {
            this.setWidth(10);
            this.setHeight(verticalMapSize + 100);
        } else {
            this.setWidth(horizontalMapSize + 100);
            this.setHeight(10);
        }
        updateWidthHitboxes(this.getWidth());
        updateHeightHitboxes(this.getHeight());
    }

    public void updateVelocity() {
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
        moveDirection(side);
    }

    private void moveDirection(int side) {
        HitBox hitBox = getHitBoxes().get(0);
        switch (side) {
            case 0: // Bottom of the screen
                targetDirection(0, -50);
                hitBox.setPosition(-50, verticalMapSize + 50);
                isVertical = false;
                break;
            case 1: // Right side of the screen
                targetDirection(-50, 0);
                hitBox.setPosition(horizontalMapSize + 50, -50);
                isVertical = true;
                break;
            case 2: // Top of the screen
                targetDirection(0, verticalMapSize);
                hitBox.setPosition(-50, -50);
                isVertical = false;
                break;
            default: // Left of the screen
                targetDirection(horizontalMapSize, 0);
                hitBox.setPosition(-50, -50);
                isVertical = true;
                break;
        }
    }

    public void targetDirection(double horizontal, double vertical) {
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
