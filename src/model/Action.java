package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Action {
	private final int amountOfNumbersCrossed;
	private final int amountOfNumbersMissed;
	private final int pointsEarned;
	private boolean passedTurn;
	private final int actionNumber;


	public Action(int amountOfNumbersCrossed, int amountOfNumbersMissed, int pointsEarned, int actionNumber) {
		this.amountOfNumbersCrossed = amountOfNumbersCrossed;
		this.amountOfNumbersMissed = amountOfNumbersMissed;
		this.pointsEarned = pointsEarned;
		this.actionNumber = actionNumber;
		passedTurn = false;
	}

	/**
	 Constructor for passed turns
	 */
	public Action(int actionNumber) {
		this(0, 0, 0, actionNumber);
		passedTurn = true;
	}

	public int getAmountOfNumbersCrossed() {
		return amountOfNumbersCrossed;
	}

	public int getAmountOfNumbersMissed() {
		return amountOfNumbersMissed;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public boolean isPassedTurn() {
		return passedTurn;
	}

	public int getActionNumber() {
		return actionNumber;
	}

	public void save(Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO action (turn_id, action_number, amount_of_numbers_missed, passed_turn, points_earned)
					VALUES (CURRVAL('turn_turn_id_seq'),?,?,?,?)
					""");
			statement.setInt(1, getAmountOfNumbersMissed());
			statement.setInt(2, getActionNumber());
			statement.setBoolean(3, isPassedTurn());
			statement.setInt(4, getPointsEarned());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
