package App.src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class PlayerSession {
	private Player player;
	private ScoreCard scoreCard;
	private LinkedList<Turn> turns;
	private boolean activePlayer;

	public PlayerSession(String name, boolean startingPlayer) {
		player = new Player(name);
		scoreCard = new ScoreCard(this);
		turns = new LinkedList<>();
		activePlayer = startingPlayer;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}

	public Turn getCurrentTurn() {
		return turns.getLast();
	}

	public void newTurn() {
		turns.add(new Turn(turns.size() + 1));
	}

	public void takeAction(int numbersCrossed, int numbersMissed, int pointsEarned) {
		turns.getLast().takeAction(numbersCrossed, numbersMissed, pointsEarned);
	}

	public void takePenaltyAction() {
		turns.getLast().takePenaltyAction();
	}

	public void passAction() {
		turns.getLast().passAction();
	}

	public Player getPlayer() {
		return player;
	}

	public String getPlayerName() {
		return player.getName();
	}

	public boolean isActivePlayer() {
		return activePlayer;
	}

	public void changeActivePlayer() {
		activePlayer = !activePlayer;
	}

	public void storeAction(int session_id, int game_id, int player_id, boolean starting_first) {
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/qwixx1",
					"postgres",
					"Student_1234");
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO player_session values (default,game_id,player_id,?)" +
					isActivePlayer());
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}

