package tennisKata;

import java.util.ArrayList;

import tennisKata.beans.Player;
import tennisKata.managers.MatchScoreManager;
import tennisKata.managers.PointManager;

public class TennisMatch {

    public static void main(String[] args) {
        Player playerOne = new Player(1, "playerOne", 123);
        Player playerTwo = new Player(2, "playerTwo", 111);
        
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        PointManager pointManager = new PointManager(players);
        MatchScoreManager scoreManager = new MatchScoreManager(playerOne, playerTwo, 3);
        while(!scoreManager.isMatchFinished()) {
            scoreManager.updateScore(pointManager.getRandomPointWinner());
            scoreManager.printCurrentScore();
        }
    }

}

