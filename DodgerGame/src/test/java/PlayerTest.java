import Model.Entities.Player.Player;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class PlayerTest {

    Player player;

    /**
     * @authors Irja Vuorela & Viktor Sundberg
     */
    @Before
    public void init() {
        player = new Player();
    }

    /**
     * @authors Viktor & Irja
     */
    @Test
    public void testGainShield() {
        int oldShields = player.getNrOfShields();
        player.gainShield(1);
        int newShields = player.getNrOfShields();
        assertTrue(newShields > oldShields);
    }

    /**
     * Test that the health power up increases the health.
     *
     * @author Olle Westerlund, Irja Vuorela, Viktor Sundberg
     */
    @Test
    public void testGainHealth() {
        player.setHp(player.getMaxHp() / 2);
        int oldHealth = player.getHp();
        player.gainHealth(1);
        int newHealth = player.getHp();
        assertTrue(newHealth > oldHealth);
    }

    /**
     * Checks that player can't heal beyond the set max hp value
     *
     * @authors Irja & Viktor
     */
    @Test
    public void didNotExceedMaxHp() {
        player.setHp(player.getMaxHp() - 1);
        player.gainHealth(player.getMaxHp());
        assertTrue(player.getHp() == player.getMaxHp());
    }
}
