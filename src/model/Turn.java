package src.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

	public void endTurn() {
		setTurnEndTime();
		turnDuration = turnEndTime - turnStartTime;
	}

	public void passAction() {
		actions.add(new Action());
	}

	public void takePenaltyAction() {
		takeAction(0, 0, -5);
	}

	public void takeAction(int amountOfNumbersCrossed, int amountOfNumbersMissed, int pointsEarned) {
		actions.add(new Action(amountOfNumbersCrossed, amountOfNumbersMissed, pointsEarned));
	}

	public int getNumberOfActions() {
		return actions.size();
	}

	public long getTurnDuration() {
		return turnDuration;
	}

	public Action getLastAction() {
		return actions.getLast();
	}

	public long getCurrentTurnDuration() {
		return System.currentTimeMillis() - turnStartTime;
	}

	public void save(Connection connection) {
		try {

			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO turn values (default,session_id,?)" + getTurnDuration());
			for (Action action : actions) {
				action.save(connection);
			}
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}