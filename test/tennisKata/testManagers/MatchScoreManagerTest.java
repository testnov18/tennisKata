package tennisKata.testManagers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tennisKata.beans.Player;
import tennisKata.beans.PlayersScore;
import tennisKata.managers.MatchScoreManager;
import tennisKata.managers.PlayersScoreManager;


class MatchScoreManagerTest {
    PlayersScoreManager testPSM1;
    PlayersScoreManager testPSM2;
    MatchScoreManager testMSM;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        testPSM1 = new PlayersScoreManager();
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 4, 6, 1));    
        testPSM2 = new PlayersScoreManager();
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 3, 6, 0));    
        testMSM = new MatchScoreManager(testPSM1.getPlayersScore().getPlayer(),
                testPSM2.getPlayersScore().getPlayer(), 3);
        testMSM.setPlayerOneScoreManager(testPSM1);
        testMSM.setPlayerTwoScoreManager(testPSM2);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    /*
     * tiebreak on
     * Player1, 4, 6, 1;    
     * Player2, 3, 6, 0;
     * operation : +1 points Player1    
     * expected
     * Player1, 5, 6, 1;    
     * Player2, 3, 6, 0;
     */
    @Test
    void testTieBreakIncrementPoint() {
        testMSM.setTieBreakCountMode(true);
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer());
        assertEquals(5, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(6, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(1, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(3, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(6, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());

    }
    /*
     * tiebreak on
     * Player1, 4, 6, 1;    
     * Player2, 3, 6, 0;
     * operation : +3 points Player1    
     * expected
     * Player1, 0, 0, 2;    
     * Player2, 0, 0, 0;
     */
    @Test
    void testTieBreakIncrementGameAndSet() {
        testMSM.setTieBreakCountMode(true);
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer()); //p1 5
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer()); //p1 6
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer()); //p1 tiebreak won
        assertEquals(0, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(2, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(0, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
        
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer());
        testMSM.printCurrentScore();
    }
    
    /*
     * tiebreak off
     * Player1, 2, 5, 0;    
     * Player2, 3, 3, 0;
     * operation : +1 points Player1    
     * expected
     * Player1, 3, 5, 0;    
     * Player2, 3, 3, 0;
     */
    @Test
    void testIncrementPoint_pointWon() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 2, 5, 0));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 3, 3, 0));
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer());
        assertEquals(3, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(5, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(3, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(3, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }
    
    
    /*
     * tiebreak off
     * Player1, 4, 5, 0;    
     * Player2, 3, 3, 0;
     * operation : +1 points Player1    
     * expected
     * Player1, 0, 0, 1;    
     * Player2, 0, 0, 0;
     */
    @Test
    void testIncrementPoint_setPointWon() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 4, 5, 0));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 3, 3, 0));
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(1, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(0, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }

    /*
     * tiebreak off
     * Player1, 3, 5, 1;    
     * Player2, 4, 5, 0;
     * operation : +1 points Player1    
     * expected
     * Player1, 3, 5, 1;    
     * Player2, 3, 5, 0;
     */
    @Test
    void testIncrementPoint_pointWonForDeuce() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 3, 5, 1));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 4, 5, 0));
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer());
        assertEquals(3, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(5, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(1, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(3, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(5, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }
    
    /*
     * tiebreak off
     * Player1, 3, 5, 1;    
     * Player2, 4, 5, 0;
     * operation : +1 points Player2    
     * expected
     * Player1, 0, 5, 1;    
     * Player2, 0, 6, 0;
     */
    @Test
    void testIncrementPoint_deuceGamePointWon() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 3, 5, 1));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 4, 5, 0));
        testMSM.updateScore(testPSM2.getPlayersScore().getPlayer());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(5, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(1, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(0, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(6, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }
    
    /*
     * tiebreak off
     * Player1, 2, 5, 1;    
     * Player2, 3, 5, 0;
     * operation : +1 points Player2    
     * expected
     * Player1, 0, 5, 1;    
     * Player2, 0, 6, 0;
     */
    @Test
    void testIncrementPoint_gamePointWon() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 2, 5, 1));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 3, 5, 0));
        testMSM.updateScore(testPSM2.getPlayersScore().getPlayer());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(5, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(1, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(0, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(6, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }
    
    /*
     * tiebreak off
     * Player1, 3, 5, 1;    
     * Player2, 3, 5, 0;
     * operation : +1 points Player2    
     * expected
     * Player1, 3, 5, 1;    
     * Player2, 4, 5, 0;
     */
    @Test
    void testIncrementPoint_gamePointWonForAD() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 3, 5, 1));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 3, 5, 0));
        testMSM.updateScore(testPSM2.getPlayersScore().getPlayer());
        assertEquals(3, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(5, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(1, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(4, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(5, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }
    
    /*
     * tiebreak off
     * Player1, 3, 5, 2;    
     * Player2, 2, 4, 0;
     * operation : +1 points Player1    
     * expected
     * Player1, 0, 0, 3;    
     * Player2, 0, 0, 0;
     */
    @Test
    void testIncrementPoint_matchPoint() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 3, 5, 2));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 2, 4, 0));
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(3, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(0, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }
    
    /*
     * tiebreak off
     * Player1, 4, 5, 2;    
     * Player2, 3, 4, 0;
     * operation : +1 points Player1    
     * expected
     * Player1, 0, 0, 3;    
     * Player2, 0, 0, 0;
     */
    @Test
    void testIncrementPoint_matchPointFromAD() {
        testMSM.setTieBreakCountMode(false);
        testPSM1.setPlayersScore(new PlayersScore(new Player(), 4, 5, 2));    
        testPSM2.setPlayersScore(new PlayersScore(new Player(), 3, 4, 0));
        testMSM.updateScore(testPSM1.getPlayersScore().getPlayer());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM1.getPlayersScore().getCurrentGamesCount());
        assertEquals(3, testPSM1.getPlayersScore().getCurrentSetsCount());
        
        assertEquals(0, testPSM2.getPlayersScore().getCurrentPointsCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentGamesCount());
        assertEquals(0, testPSM2.getPlayersScore().getCurrentSetsCount());
    }
    
    
    
    
}