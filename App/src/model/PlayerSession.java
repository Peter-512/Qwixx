package App.src.model;

import java.util.LinkedList;

public class PlayerSession {
	private Player player;
	private ScoreCard scoreCard;
	private LinkedList<Turn> turns;
	private boolean activePlayer;

	public PlayerSession(String name, boolean startingPlayer) {
		player = new Player(name);
		scoreCard = new ScoreCard(this);
		turns = new LinkedList<>();
		activePlayer = startingPlayer;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}

	public Turn getCurrentTurn() {
		return turns.getLast();
	}

	public void newTurn() {
		turns.add(new Turn(turns.size() + 1));
	}

	public void takeAction(int numbersCrossed, int numbersMissed, int pointsEarned) {
		turns.getLast().takeAction(numbersCrossed, numbersMissed, pointsEarned);
	}

	public void takePenaltyAction() {
		turns.getLast().takePenaltyAction();
	}

	public void passAction() {
		turns.getLast().passAction();
	}

	public Player getPlayer() {
		return player;
	}

	public String getPlayerName() {
		return player.getName();
	}

	public boolean isActivePlayer() {
		return activePlayer;
	}

	public void changeActivePlayer() {
		activePlayer = !activePlayer;
	}
}
