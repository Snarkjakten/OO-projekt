package Model.Entities.Projectiles;

import Model.Entities.HitBox;
import Model.GameWorld;
import Model.Movement.AbstractGameObject;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class LaserBeam extends Projectile {
    private boolean isVertical;
    private final int damage = 25;
    private final double horizontalMapSize = GameWorld.getInstance().getPlayingFieldWidth();
    private final double verticalMapSize = GameWorld.getInstance().getPlayingFieldHeight();

    /**
     * @author Olle Westerlund
     * Constructor for a random laser beam
     */
    public LaserBeam() {
        super(100, 1, 1);
        randomStartPoint();
        initSize();
    }

    /**
     * @author Olle Westerlund
     * Constructor for a specified laser beam.
     * @param side the side that the laser beam will spawn on.
     */
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

    /**
     * @author Olle Westerlund
     * The method sets the right size depending on if the laser beam
     * is vertical or not.
     */
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

    /**
     * @author Olle Westerlund
     * The method returns a random side for the laser beam
     * to spawn on.
     */
    private void randomStartPoint() {
        Random random = new Random();
        int side = random.nextInt(4);
        moveDirection(side);
    }

    /**
     * @author Olle Westerlund
     * @param side The side that the laser beam is spawning on.
     */
    private void moveDirection(int side) {
        HitBox hitBox = getHitBoxes().get(0);
        switch (side) {
            case 0: // Bottom of the screen
                setVelocity(0, -50);
                hitBox.setPosition(-50, verticalMapSize + 50);
                isVertical = false;
                break;
            case 1: // Right side of the screen
                setVelocity(-50, 0);
                hitBox.setPosition(horizontalMapSize + 50, -50);
                isVertical = true;
                break;
            case 2: // Top of the screen
                setVelocity(0, verticalMapSize);
                hitBox.setPosition(-50, -50);
                isVertical = false;
                break;
            default: // Left of the screen
                setVelocity(horizontalMapSize, 0);
                hitBox.setPosition(-50, -50);
                isVertical = true;
                break;
        }
    }

    public int getDamage() {
        return damage;
    }

    public boolean isVertical() {
        return isVertical;
    }

    /**
     * Removes projectiles collided with asteroid unless object is an instance of laserbeam
     *
     * @param c the type of object this object has collided with
     */
    @Override
    public void actOnCollision(AbstractGameObject c) { }
}
