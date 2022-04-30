package App.src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Action {
	private int amountOfNumbersCrossed;
	private int amountOfNumbersMissed;
	private int pointsEarned;
	private boolean passedTurn;

	public Action(int amountOfNumbersCrossed, int amountOfNumbersMissed, int pointsEarned) {
		this.amountOfNumbersCrossed = amountOfNumbersCrossed;
		this.amountOfNumbersMissed = amountOfNumbersMissed;
		this.pointsEarned = pointsEarned;
		passedTurn = false;
	}

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
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO action values (default,turn_id,?,?,?)" +
					getAmountOfNumbersMissed() +
					isPassedTurn() +
					getPointsEarned());
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
