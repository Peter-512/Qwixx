package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Action {
	private int amountOfNumbersCrossed;
	private int amountOfNumbersMissed;
	private int pointsEarned;
	private boolean passedTurn;

	/**
	 @param amountOfNumbersCrossed
	 @param amountOfNumbersMissed
	 @param pointsEarned
	 */
	public Action(int amountOfNumbersCrossed, int amountOfNumbersMissed, int pointsEarned) {
		this.amountOfNumbersCrossed = amountOfNumbersCrossed;
		this.amountOfNumbersMissed = amountOfNumbersMissed;
		this.pointsEarned = pointsEarned;
		passedTurn = false;
	}

	/**
	 Constructor for passed turns
	 */
	public Action() {
		this(0, 0, 0);
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

	public void save(Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO action (turn_id, amount_of_numbers_missed, passed_turn, points_earned)
					VALUES (CURRVAL('turn_turn_id_seq'),?,?,?)
					""");
			statement.setInt(1, getAmountOfNumbersMissed());
			statement.setBoolean(2, isPassedTurn());
			statement.setInt(3, getPointsEarned());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
