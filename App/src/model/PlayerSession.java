package App.src.model;

import java.util.LinkedList;

public class PlayerSession {
	private Player player;
	private ScoreCard scoreCard;
	private LinkedList<Action> actions;
	private boolean startingPlayer;

	public PlayerSession(String name, boolean startingPlayer) {
		player = new Player(name);
		scoreCard = new ScoreCard();
		actions = new LinkedList<>();
		this.startingPlayer = startingPlayer;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}

	public Action getCurrentAction() {
		return actions.getLast();
	}

	public void addAction() {
		actions.add(new Action());
	}

	public Player getPlayer() {
		return player;
	}

	public String getPlayerName() {
		return player.getName();
	}
}
