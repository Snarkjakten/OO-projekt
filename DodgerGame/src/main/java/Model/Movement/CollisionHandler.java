package Model.Movement;

/**
 * Checks hitboxes Rectangle2D for intersection (collision)
 * Calls class specific actOnCollision methods to execute on impact
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandler {

    public boolean checkCollision(AbstractGameObject g, AbstractGameObject a){
        return g.getHitbox().intersects(a.getHitbox()) && a != g;
    }

    public void collide(AbstractGameObject g, AbstractGameObject a) {
        g.actOnCollision(a);
        a.actOnCollision(g);
    }
}
