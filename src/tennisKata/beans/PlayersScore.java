package tennisKata.beans;

public class PlayersScore {
    private Player player;
    
	int currentPointsCount;
    int currentGamesCount;
    int currentSetsCount;
    
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public int getCurrentPointsCount() {
        return currentPointsCount;
    }
    public void setCurrentPointsCount(int currentPointsCount) {
        this.currentPointsCount = currentPointsCount;
    }
    public int getCurrentGamesCount() {
        return currentGamesCount;
    }
    public void setCurrentGamesCount(int currentGamesCount) {
        this.currentGamesCount = currentGamesCount;
    }
    public int getCurrentSetsCount() {
        return currentSetsCount;
    }
    public void setCurrentSetsCount(int currentSetsCount) {
        this.currentSetsCount = currentSetsCount;
    }
    
    public PlayersScore(Player player) {
        super();
        this.player = player;
        this.currentPointsCount = 0;
        this.currentGamesCount = 0;
        this.currentSetsCount = 0;
    }
    
    public PlayersScore(Player player, int currentPointsCount, int currentGamesCount, int currentSetsCount) {
		super();
		this.player = player;
		this.currentPointsCount = currentPointsCount;
		this.currentGamesCount = currentGamesCount;
		this.currentSetsCount = currentSetsCount;
	}

    
    public void incrementPointsCount() {
        this.currentPointsCount++;
    }
    
    public void decreasePointsCountInDeuce() {
        this.currentPointsCount--;
    }
    
    public void incrementGamesCount() {
        this.currentGamesCount++;
        this.resetPointsCount();
        
    }
    public void incrementSetsCount() {
        this.currentSetsCount++;
        this.resetPointsCount();
        this.resetGamesCount();
    }
    
    public void resetPointsCount() {
        this.currentPointsCount=0;
    }
    
    public void resetGamesCount() {
        this.currentGamesCount=0;
        this.currentPointsCount=0;
    }
    
    public void printCurrentPlayersScore() {
        System.out.println(this.player.getPlayerName() + "  "+this.currentPointsCount+"-"+
                    this.currentGamesCount+"-"+this.currentSetsCount);
    }
}

