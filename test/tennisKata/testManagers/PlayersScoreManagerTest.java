package tennisKata.testManagers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tennisKata.beans.Player;
import tennisKata.beans.PlayersScore;
import tennisKata.managers.PlayersScoreManager;


class PlayersScoreManagerTest {
    PlayersScoreManager psm;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        psm = new PlayersScoreManager();
        psm.setPlayersScore(new PlayersScore(new Player(), 3, 5, 1));    
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    
    @Test
    void testIncrementPointsCount() {
        psm.incrementPointsCount();
        assertEquals(4, psm.getPlayersScore().getCurrentPointsCount());
    }
    
    @Test
    void testDecreasePointsCountInDeuce() {
        psm.decreasePointsCountInDeuce();
        assertEquals(2, psm.getPlayersScore().getCurrentPointsCount());
    }
    
    @Test
    void TestIncrementGamesCount() {
        psm.incrementGamesCount();
        assertEquals(0, psm.getPlayersScore().getCurrentPointsCount());
        assertEquals(6, psm.getPlayersScore().getCurrentGamesCount());
    }
    
    @Test
    void testIncrementSetsCount() {
        psm.incrementSetsCount();
        assertEquals(0, psm.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, psm.getPlayersScore().getCurrentGamesCount());
        assertEquals(2, psm.getPlayersScore().getCurrentSetsCount());
    }

}
