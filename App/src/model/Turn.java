package App.src.model;

import java.util.Date;
import java.util.LinkedList;

public class Turn {
	private final int turnNumber;
	private final long turnStartTime;
	private long turnEndTime;
	private long turnDuration;
	private LinkedList<Action> actions;

	public Turn(int turnNumber) {
		this.turnNumber = turnNumber;
		turnStartTime = new Date().getTime();
		actions = new LinkedList<>();
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	private void setTurnEndTime() {
		turnEndTime = new Date().getTime();
	}

	public void setTurnDuration() {
		setTurnEndTime();
		turnEndTime = turnEndTime - turnStartTime;
	}

	public long getCurrentTurnDuration() {
		return new Date().getTime() - turnStartTime;
	}

	public void addAction(Action action) {
		actions.add(action);
	}
}
