package tennisKata.managers;

import java.util.List;
import java.util.Random;

import tennisKata.beans.Player;

public class PointManager {
	   private List<Player> playersList;
	    
	    public PointManager(List<Player> playersList) {
	        this.playersList = playersList;
	    }
	    
	    public Player getRandomPointWinner() {
	        Random r = new Random();
	        int playerPosition = r.nextInt(playersList.size());
	        return playersList.get(playerPosition);
	    }
}
