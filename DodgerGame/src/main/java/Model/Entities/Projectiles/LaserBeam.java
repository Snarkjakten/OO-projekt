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
        setSpeed(100);
        randomStartSide();
        initSize();
    }

    /**
     * @param side the side that the laser beam will spawn on.
     * @author Olle Westerlund
     * Constructor for a specified laser beam.
     */
    public LaserBeam(int side) {
        setSpeed(100);
        moveDirection(side);
        initSize();
    }

    /**
     * @author Olle Westerlund
     * The method sets the right size depending on if the laser beam
     * is vertical or not.
     */
    private void initSize() {
        if (isVertical) {
            this.setWidthHitBoxes(10);
            this.setHeightHitBoxes(verticalMapSize + 100);
        } else {
            this.setWidthHitBoxes(horizontalMapSize + 100);
            this.setHeightHitBoxes(10);
        }
    }

    /**
     * @author Olle Westerlund
     * The method returns a random side for the laser beam
     * to spawn on.
     */
    private void randomStartSide() {
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
                setVelocity(0, -1);

                hitBox.setPosition(-50, verticalMapSize + 50);
                isVertical = false;
                break;
            case 1: // Right side of the screen
                setVelocity(-1, 0);
                hitBox.setPosition(horizontalMapSize + 50, -50);
                isVertical = true;
                break;
            case 2: // Top of the screen
                setVelocity(0, 1);
                hitBox.setPosition(-50, -50);
                isVertical = false;
                break;
            default: // Left of the screen
                setVelocity(1, 0);
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
}
