package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class PlayerSession {
	private final Player player;
	private final ScoreCard scoreCard;
	private final LinkedList<Turn> turns;
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
		if (turns.isEmpty()) {
			return null;
		}
		return turns.getLast();
	}

	public void newTurn() {
		turns.add(new Turn(turns.size() + 1));
	}

	public void takeAction(int numbersMissed, int pointsEarned) {
		turns.getLast().takeAction(numbersMissed, pointsEarned);
	}

	public void takePenaltyAction() {
		turns.getLast().takePenaltyAction();
		scoreCard.addPenalty();
	}

	public void passAction() {
		turns.getLast().passAction();
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

	public void save(Connection connection) {
		try {
			player.save(connection);
			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO player_session (game_id, player_id, starting_first)
					VALUES (CURRVAL('game_session_game_id_seq'), CURRVAL('player_player_id_seq'), ?)
					""");
			boolean startingPlayer = (turns.size() % 2 == 1) != activePlayer;
			statement.setBoolean(1, startingPlayer);
			statement.executeUpdate();
			scoreCard.save(connection);
			for (Turn turn : turns) {
				turn.save(connection);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
