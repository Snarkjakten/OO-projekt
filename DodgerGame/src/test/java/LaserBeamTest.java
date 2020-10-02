import Entities.LaserBeam;
import org.junit.Before;

public class LaserBeamTest {

    LaserBeam laser;

    @Before
    //@Author Olle Westerlund
    public void init() {
        laser = new LaserBeam( 0.1);
    }

}
