package Model.Entities.Projectiles;

import Model.Entities.HitBox;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class LaserBeam extends Projectile {
    private boolean isVertical;
    private final int damage = 25;

    /**
     * @author Olle Westerlund
     * Constructor for a random laser beam
     */
    public LaserBeam(double playingFieldWidth, double playingFieldHeight) {
        setSpeed(100);
        randomStartSide(playingFieldWidth, playingFieldHeight);
        initSize(1200, 1200);
    }

    /**
     * @param side the side that the laser beam will spawn on.
     * @author Olle Westerlund
     * Constructor for a specified laser beam.
     */
    public LaserBeam(int side, double playingFieldWidth, double playingFieldHeight) {
        setSpeed(100);
        moveDirection(side, playingFieldWidth, playingFieldHeight);
        initSize(1200, 1200);
    }

    /**
     * @author Olle Westerlund
     * The method sets the right size depending on if the laser beam
     * is vertical or not.
     */
    private void initSize(double playingFieldWidth, double playingFieldHeight) {
        if (isVertical) {
            this.setWidthHitBoxes(10);
            this.setHeightHitBoxes(playingFieldHeight * 2);
        } else {
            this.setWidthHitBoxes(playingFieldWidth + 100);
            this.setHeightHitBoxes(10);
        }
    }

    /**
     * @author Olle Westerlund
     * The method returns a random side for the laser beam
     * to spawn on.
     */
    private void randomStartSide(double playingFieldWidth, double playingFieldHeight) {
        Random random = new Random();
        int side = random.nextInt(4);
        moveDirection(side, playingFieldWidth, playingFieldHeight);
    }

    /**
     * @param side The side that the laser beam is spawning on.
     * @author Olle Westerlund
     */
    private void moveDirection(int side, double playingFieldWidth, double playingFieldHeight) {
        HitBox hitBox = getHitBoxes().get(0);
        switch (side) {
            case 0: // Bottom of the screen
                setVelocity(0, -1);
                hitBox.updateHitBoxPosition(-50, playingFieldHeight + 50);
                isVertical = false;
                break;
            case 1: // Right side of the screen
                setVelocity(-1, 0);
                hitBox.updateHitBoxPosition(playingFieldWidth + 50, -50);
                isVertical = true;
                break;
            case 2: // Top of the screen
                setVelocity(0, 1);
                hitBox.updateHitBoxPosition(-50, -50);
                isVertical = false;
                break;
            default: // Left of the screen
                setVelocity(1, 0);
                hitBox.updateHitBoxPosition(-50, -50);
                isVertical = true;
                break;
        }
    }

    @Override
    public int getAmount() {
        return damage;
    }

    public boolean getIsVertical() {
        return isVertical;
    }

    @Override
    public void actOnCollision(String className, int amount) {
    }

    @Override
    public String toString() {
        return "LaserBeam";
    }
}
