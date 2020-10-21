package Model.Entities.Projectiles;

import Model.Movement.AbstractGameObject;

/**
 * @author Isak Almeros
 */

public class SlowDebuff extends Projectile {
    private final double slowSpeedFactor;

    public SlowDebuff() {
        super(300, 32, 32);
        slowSpeedFactor = 0.75;
    }

    public double getSlowSpeedFactor() {
        return slowSpeedFactor;
    }

    /**
     * Removes projectiles collided with asteroid unless object is an instance of laserbeam
     *
     * @param c the type of object this object has collided with
     */
    @Override
    public void actOnCollision(AbstractGameObject c) {
        /*if(!(c instanceof LaserBeam || c instanceof Spaceship)) {
            c.setCollided(true);
        }*/
    }
}
