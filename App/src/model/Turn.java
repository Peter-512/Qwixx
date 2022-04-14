package App.src.model;

import java.util.LinkedList;

public class Turn {
	private final int turnNumber;
	private final long turnStartTime;
	private long turnEndTime;
	private long turnDuration;
	private LinkedList<Action> actions;

	public Turn(int turnNumber) {
		this.turnNumber = turnNumber;
		turnStartTime = System.currentTimeMillis();
		actions = new LinkedList<>();
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	private void setTurnEndTime() {
		turnEndTime = System.currentTimeMillis();
	}

	public void setTurnDuration() {
		setTurnEndTime();
		turnEndTime = turnEndTime - turnStartTime;
	}

	public Action getCurrentAction() {
		return actions.getLast();
	}

	public long getCurrentTurnDuration() {
		return System.currentTimeMillis() - turnStartTime;
	}

	public void addAction(Action action) {
		actions.add(action);
	}
}
