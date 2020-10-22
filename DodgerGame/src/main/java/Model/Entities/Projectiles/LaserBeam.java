package Model.Entities.Projectiles;

import Model.Entities.HitBox;
import Model.GameWorld;

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
     * @param side the side that the laser beam will spawn on.
     * @author Olle Westerlund
     * Constructor for a specified laser beam.
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
            setWidth(10);
            setHeight(verticalMapSize + 100);
        } else {
            setWidth(horizontalMapSize + 100);
            setHeight(10);
        }
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
     * @param side The side that the laser beam is spawning on.
     * @author Olle Westerlund
     */
    private void moveDirection(int side) {
        HitBox hitBox = getHitBoxes().get(0);
        switch (side) {
            case 0: // Bottom of the screen
                targetDirection(0, -50);
                hitBox.updateHitBox(-50, verticalMapSize + 50, getWidth(), getHeight());
                isVertical = false;
                break;
            case 1: // Right side of the screen
                targetDirection(-50, 0);
                hitBox.updateHitBox(horizontalMapSize + 50, 50, getWidth(), getHeight());
                isVertical = true;
                break;
            case 2: // Top of the screen
                targetDirection(0, verticalMapSize);
                hitBox.updateHitBox(-50, -50, getWidth(), getHeight());
                isVertical = false;
                break;
            default: // Left of the screen
                targetDirection(horizontalMapSize, 0);
                hitBox.updateHitBox(-50, -50, getWidth(), getHeight());
                isVertical = true;
                break;
        }
    }

    /**
     * @param horizontal the horizontal value to move towards
     * @param vertical   the vertical value to move towards
     * @author Olle Westerlund
     */
    public void targetDirection(double horizontal, double vertical) {
        this.setVelocity(horizontal, vertical);
    }

    public int getDamage() {
        return damage;
    }

    public boolean isVertical() {
        return isVertical;
    }
}
