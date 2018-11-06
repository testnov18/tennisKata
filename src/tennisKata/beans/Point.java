package tennisKata.beans;

public class Point {
    private int pointNbr;
    private Player playerServed;
    private Player playerWon;
    int rallyLength;
    int duration;
    int pointSet;
    int pointGame;
    
    public int getPointNbr() {
        return pointNbr;
    }
    public void setPointNbr(int pointNbr) {
        this.pointNbr = pointNbr;
    }
    public Player getPlayerServed() {
        return playerServed;
    }
    public void setPlayerServed(Player playerServed) {
        this.playerServed = playerServed;
    }
    public Player getPlayerWon() {
        return playerWon;
    }
    public void setPlayerWon(Player playerWon) {
        this.playerWon = playerWon;
    }
    public int getRallyLength() {
        return rallyLength;
    }
    public void setRallyLength(int rallyLength) {
        this.rallyLength = rallyLength;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getPointSet() {
        return pointSet;
    }
    public void setPointSet(int pointSet) {
        this.pointSet = pointSet;
    }
    public int getPointGame() {
        return pointGame;
    }
    public void setPointGame(int pointGame) {
        this.pointGame = pointGame;
    }
    
    public Point(int pointNbr, Player playerServed, Player playerWon, int rallyLength, int duration, int pointSet,
            int pointGame) {
        super();
        this.pointNbr = pointNbr;
        this.playerServed = playerServed;
        this.playerWon = playerWon;
        this.rallyLength = rallyLength;
        this.duration = duration;
        this.pointSet = pointSet;
        this.pointGame = pointGame;
    }
    
    
    
}
