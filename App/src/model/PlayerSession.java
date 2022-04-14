package App.src.model;

import java.util.LinkedList;

public class PlayerSession {
	private Player player;
	private ScoreCard scoreCard;
	private LinkedList<Action> actions;
	private boolean activePlayer;

	public PlayerSession(String name, boolean startingPlayer) {
		player = new Player(name);
		scoreCard = new ScoreCard();
		actions = new LinkedList<>();
		activePlayer = startingPlayer;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}

	public Action getCurrentAction() {
		return actions.getLast();
	}

	private void addAction(Action action) {
		actions.add(action);
	}

	public void passAction() {
		addAction(new Action());
	}

	public void takeAction(int amountOfNumbersCrossed, int amountOfNumbersMissed, int pointsEarned) {
		addAction(new Action(amountOfNumbersCrossed, amountOfNumbersMissed, pointsEarned));
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
