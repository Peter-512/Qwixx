package App.src.model;

import java.util.Date;

public class Turn {
	private final int turnNumber;
	private final long turnStartTime;
	private long turnEndTime;
	private long turnDuration;

	public Turn(int turnNumber) {
		this.turnNumber = turnNumber;
		turnStartTime = new Date().getTime();
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
}
