package Model.Movement;

import Model.Entities.HitBox;

/**
 * Checks hitBoxes Rectangle2D for intersection (collision)
 * Calls class specific actOnCollision methods to execute on impact
 *
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandler {

    public boolean checkCollision(AbstractGameObject g, AbstractGameObject a) {
        for (HitBox hitBox1 : g.getHitBoxes())
            for (HitBox hitBox2 : a.getHitBoxes())
                return hitBox1.getHitBox().intersects(hitBox2.getHitBox()) && a != g;
        return false;
    }

    public void collide(AbstractGameObject g, AbstractGameObject a) {
        g.actOnCollision(a);
        a.actOnCollision(g);
    }
}
