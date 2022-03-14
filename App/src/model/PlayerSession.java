package App.src.model;

import java.util.LinkedList;

public class PlayerSession {
	private ScoreCard scoreCard;
	private LinkedList<Action> actions;

	public PlayerSession() {
		scoreCard = new ScoreCard();
		actions = new LinkedList<>();
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
}
