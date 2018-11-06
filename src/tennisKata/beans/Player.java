package tennisKata.beans;

public class Player{
    private int playerId;
    private String playerName;
    private int playerAtpRank;

    public int getPlayerId(){
      return this.playerId;
    }
    public String getPlayerName(){
      return this.playerName;
    }
    public int getPlayerAtpRank(){
      return this.playerAtpRank;
    }

    public Player() {
		super();
	}
    
	public Player(int playerId, String playerName, int playerAtpRank){
      this.playerId = playerId;
      this.playerName = playerName;
      this.playerAtpRank = playerAtpRank;
    }
  }