package tennisKata.managers;

import tennisKata.beans.Player;

public class MatchScoreManager {
	private final int MIN_POINTS_TO_WIN_GAME = 4;
	private final int MIN_GAMES_TO_WIN_SET = 6;
	private final int MIN_POINTS_TO_WIN_TIEBREAK_GAME = 7;
	private final int MIN_GAMES_DIFF_FOR_NORMAL_SET=2;
	private final int MIN_GAMES_DIFF_FOR_TIEBREAK_SET=1;
	private int nbrSetsWons;
	private boolean isTieBreakCountMode = false;
	
	public boolean isTieBreakCountMode() {
		return isTieBreakCountMode;
	}

	public void setTieBreakCountMode(boolean isTieBreakCountMode) {
		this.isTieBreakCountMode = isTieBreakCountMode;
	}

	private PlayersScoreManager playerOneScoreManager;
	
	public PlayersScoreManager getPlayerOneScoreManager() {
		return playerOneScoreManager;
	}

	public void setPlayerOneScoreManager(PlayersScoreManager playerOneScoreManager) {
		this.playerOneScoreManager = playerOneScoreManager;
	}

	private PlayersScoreManager playerTwoScoreManager;
	
	
	public PlayersScoreManager getPlayerTwoScoreManager() {
		return playerTwoScoreManager;
	}
	
	public void setPlayerTwoScoreManager(PlayersScoreManager playerTwoScoreManager) {
		this.playerTwoScoreManager = playerTwoScoreManager;
	}

	public MatchScoreManager (Player playerOne, Player playerTwo, int nbrSetsWons) {
		this.playerOneScoreManager = new PlayersScoreManager(playerOne);
		this.playerTwoScoreManager = new PlayersScoreManager(playerTwo);
		this.nbrSetsWons = nbrSetsWons;
	}
	
	public void updateScore(Player pointWinner) {
		if (isTieBreakCountMode) {
			countPointsTieBreakModeScore(pointWinner);
		}else {
			countPointsNormalModeScore(pointWinner);
		}
		
		if (isToIncrementGames(isTieBreakCountMode)) {
			if(isTieBreakScore()) {
				isTieBreakCountMode = true;
			}else {
				isTieBreakCountMode = false;
			}
			if (isToIncrementSets(isTieBreakCountMode)) {
				isMatchFinished(); 
			};
		}
	}

	private boolean isToIncrementGames(boolean isTieBreakMode) {
		int minPointsToWinCurrentGame;
		if (isTieBreakMode) {
			minPointsToWinCurrentGame=MIN_POINTS_TO_WIN_TIEBREAK_GAME;
		}else {
			minPointsToWinCurrentGame=MIN_POINTS_TO_WIN_GAME;
		}
		if (playerOneScoreManager.getPlayersScore().getCurrentPointsCount()>=minPointsToWinCurrentGame 
				|| getPlayerTwoScoreManager().getPlayersScore().getCurrentPointsCount()>=minPointsToWinCurrentGame) {
					if (Math.abs(playerOneScoreManager.getPlayersScore().getCurrentPointsCount()-playerTwoScoreManager.getPlayersScore().getCurrentPointsCount())>=2) {
						if(playerOneScoreManager.getPlayersScore().getCurrentPointsCount()>playerTwoScoreManager.getPlayersScore().getCurrentPointsCount()) {
							playerOneScoreManager.incrementGamesCount();
							playerTwoScoreManager.resetPointsCount();
						}
						else {
							playerTwoScoreManager.incrementGamesCount();
							playerOneScoreManager.resetPointsCount();
						}
						return true;
					}		
		}
		return false;
	}
	
	private boolean isToIncrementSets(boolean isTieBreakMode) {
		int minGamesDiffsToWinSet = MIN_GAMES_DIFF_FOR_NORMAL_SET;
		if (isTieBreakMode) {
			minGamesDiffsToWinSet=MIN_GAMES_DIFF_FOR_TIEBREAK_SET;
		}
		if (playerOneScoreManager.getPlayersScore().getCurrentGamesCount()>=MIN_GAMES_TO_WIN_SET 
				|| playerTwoScoreManager.getPlayersScore().getCurrentGamesCount()>=MIN_GAMES_TO_WIN_SET) {
					if (Math.abs(playerOneScoreManager.getPlayersScore().getCurrentGamesCount()-playerTwoScoreManager.getPlayersScore().getCurrentGamesCount())>=minGamesDiffsToWinSet) {
						if(playerOneScoreManager.getPlayersScore().getCurrentGamesCount()>playerTwoScoreManager.getPlayersScore().getCurrentGamesCount()) {
							playerOneScoreManager.incrementSetsCount();
							playerTwoScoreManager.resetGamesCount();
						}
						else {
							playerTwoScoreManager.incrementSetsCount();
							playerOneScoreManager.resetGamesCount();
						}
						return true;
					}		
		}
		return false;
	}
	
	private boolean isTieBreakScore() {
		if(playerOneScoreManager.getPlayersScore().getCurrentGamesCount()>=MIN_GAMES_TO_WIN_SET&&
				playerTwoScoreManager.getPlayersScore().getCurrentGamesCount()>=MIN_GAMES_TO_WIN_SET) {
			return true;
		}
		return false;
	}
	
	private void countPointsNormalModeScore(Player pointWinner) {
		if (pointWinner.equals(playerOneScoreManager.getPlayersScore().getPlayer())) {
			if(playerTwoScoreManager.getPlayersScore().getCurrentPointsCount()==MIN_POINTS_TO_WIN_GAME) {
				playerTwoScoreManager.decreasePointsCountInDeuce();
			}else {
				playerOneScoreManager.incrementPointsCount();
			}
		}else {
			if(playerOneScoreManager.getPlayersScore().getCurrentPointsCount()==MIN_POINTS_TO_WIN_GAME) {
				playerOneScoreManager.decreasePointsCountInDeuce();
			}else {
				playerTwoScoreManager.incrementPointsCount();
			}
		}
	}
	
	private void countPointsTieBreakModeScore(Player pointWinner) {
		if (pointWinner.equals(playerOneScoreManager.getPlayersScore().getPlayer())) {
				playerOneScoreManager.incrementPointsCount();
		}else {
				playerTwoScoreManager.incrementPointsCount();
		}
	}
	
	public boolean isMatchFinished() {
		if (playerOneScoreManager.getPlayersScore().getCurrentSetsCount()>=nbrSetsWons 
				|| playerTwoScoreManager.getPlayersScore().getCurrentSetsCount()>=nbrSetsWons) {
			return true;
		}
		return false;
	}
	
	public void printCurrentScore() {
		if (isTieBreakCountMode) {
			System.out.println("----TIEBREAK----");
		}
		playerOneScoreManager.printCurrentPlayersScore(isTieBreakCountMode);
		playerTwoScoreManager.printCurrentPlayersScore(isTieBreakCountMode);
		System.out.println("***************************");
	}
	
}
