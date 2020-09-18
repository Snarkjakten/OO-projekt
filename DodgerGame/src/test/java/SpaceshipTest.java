import Entities.Spaceship.Spaceship;
import Entities.Spaceship.SpaceshipFactory;
import Entities.Spaceship.SpaceshipGUI;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpaceshipTest {

    @Test
    public void spaceshipAndGUISamePosition() {
        Spaceship spaceship = SpaceshipFactory.createSpaceship();
        SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 200, 200);
        System.out.println(spaceship.position.getX());
        System.out.println(spaceshipGUI.getPoint().getX());
        assertTrue(spaceship.position.getX() == spaceshipGUI.getPoint().getX() && spaceship.position.getY() == spaceshipGUI.getPoint().getY());
    }
}
