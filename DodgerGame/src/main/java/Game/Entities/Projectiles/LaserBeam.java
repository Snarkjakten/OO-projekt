package Game.Entities.Projectiles;

import Game.Entities.Player.HitBox;
import Game.Movement.AbstractGameObject;

import java.util.Random;

/**
 * @author Olle Westerlund
 */
public class LaserBeam extends AbstractGameObject {
    private double horizontal;
    private double vertical;
    private boolean isVertical;
    private final double screenHorizontalLength = 800; //TODO: Get this from model.
    private final double screenVerticalLength = 600; // TODO: Get this from model.

    public LaserBeam() {
        setSpeed(100);
        getHitBoxes().add(new HitBox(0, 0, 1, 1));
        randomStartPoint();
    }

    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    private void updateVelocity() {
        HitBox hitBox = getHitBoxes().get(0);
        hitBox.setVelocity(this.horizontal, this.vertical, getSpeed());
    }

    /**
     * @author Olle Westerlund
     * The method sets a starting side and then sets the position to go to.
     */
    private void randomStartPoint() {
        HitBox hitBox = getHitBoxes().get(0);
        Random random = new Random();
        int side = random.nextInt(4);
        switch (side) {
            case 0: // Bottom of the screen
                this.isVertical = false;
                setStopPosition(0, -50);
                hitBox.updatePosition(-50, screenVerticalLength + 50);
                break;
            case 1: // Right side of the screen
                this.isVertical = true;
                setStopPosition(-50, 0);
                hitBox.updatePosition(screenHorizontalLength + 50, -50);
                break;
            case 2: // Top of the screen
                this.isVertical = false;
                setStopPosition(0, screenVerticalLength);
                hitBox.updatePosition(-50, -50);
                break;
            case 3: // Left of the screen
                this.isVertical = true;
                setStopPosition(screenHorizontalLength, 0);
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

    public double getHorizontal() {
        return horizontal;
    }

    public double getVertical() {
        return vertical;
    }

    public boolean isVertical() {
        return isVertical;
    }
}
