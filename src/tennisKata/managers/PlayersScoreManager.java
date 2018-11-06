package tennisKata.managers;

import tennisKata.beans.Player;
import tennisKata.beans.PlayersScore;

public class PlayersScoreManager {
    
    private PlayersScore playersScore;
    
    public PlayersScore getPlayersScore() {
        return this.playersScore;
    };
    
    public void setPlayersScore(PlayersScore playersScore) {
		this.playersScore = playersScore;
    }
    
    public PlayersScoreManager(Player player) {
        
        this.playersScore = new PlayersScore(player);
    }

    public PlayersScoreManager() {
    }

    public void incrementPointsCount() {
        this.playersScore.setCurrentPointsCount(this.playersScore.getCurrentPointsCount()+1);
    }
    
    public void decreasePointsCountInDeuce() {
        this.playersScore.setCurrentPointsCount(this.playersScore.getCurrentPointsCount()-1);
    }
    
    public void incrementGamesCount() {
        this.playersScore.setCurrentGamesCount(this.playersScore.getCurrentGamesCount()+1);
        this.resetPointsCount();
        
    }
    public void incrementSetsCount() {
        this.playersScore.setCurrentSetsCount(this.playersScore.getCurrentSetsCount()+1);
        this.resetPointsCount();
        this.resetGamesCount();
    }
    
    public void resetPointsCount() {
        this.playersScore.setCurrentPointsCount(0);
    }
    
    public void resetGamesCount() {
        this.playersScore.setCurrentGamesCount(0);
        this.playersScore.setCurrentPointsCount(0);
    }
    
    public void printCurrentPlayersScore(boolean isTieBreakMode) {
        String pointsCount=this.playersScore.getCurrentPointsCount()+"";
        if(!isTieBreakMode) {
            pointsCount = convertPointsToTennisFormat();
        }
        System.out.println(this.playersScore.getPlayer().getPlayerName() + "  "+pointsCount+"-"+
                    this.playersScore.getCurrentGamesCount()+"-"+this.playersScore.getCurrentSetsCount());
    }
    
    private String convertPointsToTennisFormat() {
        String points ="";
        switch (this.playersScore.getCurrentPointsCount()) {
        case 0:  points = "00";
            break;
        case 1:  points = "15";
            break;
        case 2:  points = "30";
            break;
        case 3:  points = "40";
            break;
        case 4:  points = "AD";
            break;
        case 5:  points = "WIN";
            break;
        }
        return points;
        
    }
}