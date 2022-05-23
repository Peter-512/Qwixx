package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class Turn {
	private final int turnNumber;
	private final long turnStartTime;
	private long turnEndTime;
	private int turnDuration;
	private final LinkedList<Action> actions;

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
		turnDuration = (int) (turnEndTime - turnStartTime);
	}

	public void passAction() {
		actions.add(new Action(actions.size() + 1));
	}

	public void takePenaltyAction() {
		takeAction(0, -5);
	}

	public void takeAction(int amountOfNumbersMissed, int pointsEarned) {
		actions.add(new Action(amountOfNumbersMissed, pointsEarned, actions.size() + 1));
	}

	public int getNumberOfActions() {
		return actions.size();
	}

	public int getTurnDuration() {
		return turnDuration;
	}

	public Action getLastAction() {
		return actions.getLast();
	}

	public void save(Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO turn (session_id,turn_number, turn_duration)
					VALUES (CURRVAL('player_session_session_id_seq'),?,?)
					""");
			statement.setInt(1, getTurnNumber());
			statement.setInt(2, getTurnDuration());
			statement.executeUpdate();
			for (Action action : actions) {
				action.save(connection);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
