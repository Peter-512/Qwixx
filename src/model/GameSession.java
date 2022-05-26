package src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class GameSession {
	private final PlayerSession[] playerSessions;
	private final DicePool coloredDicePool;
	private final DicePool publicDicePool;
	private boolean isHumanActivePlayer;
	private final long startTime;
	private long endTime;
	private GameState gameState;

	public GameSession(String name, boolean startingPlayer, boolean hardMode) {
		playerSessions = new PlayerSession[2];
		if (hardMode) {
			playerSessions[0] = new AI("Skynet", !startingPlayer);
		} else {
			playerSessions[0] = new BotSession("Jef", !startingPlayer);
		}
		playerSessions[1] = new PlayerSession(name, startingPlayer);
		coloredDicePool = new DicePool();
		publicDicePool = new DicePool(true);
		isHumanActivePlayer = startingPlayer;
		startTime = System.currentTimeMillis();
		endTime = 0;
		gameState = GameState.RUNNING;
	}

	public void throwAllDice() {
		coloredDicePool.throwDice();
		publicDicePool.throwDice();
	}

	public int totalPublicThrow() {
		return publicDicePool.getDice().stream().reduce(0, (subtotal, die) ->
				subtotal + die.getValue(), Integer::sum);
	}

	public PlayerSession[] getPlayerSessions() {
		return playerSessions;
	}

	public DicePool getColoredDicePool() {
		return coloredDicePool;
	}

	public DicePool getPublicDicePool() {
		return publicDicePool;
	}

	public void changeActivePlayer() {
		isHumanActivePlayer = !isHumanActivePlayer;
		for (PlayerSession playerSession : playerSessions) {
			if (playerSession.getCurrentTurn() != null) {
				playerSession.getCurrentTurn().endTurn();
			}
			playerSession.changeActivePlayer();
		}
	}

	public long getStartTime() {
		return startTime;
	}

	public void setEndTime() {
		endTime = System.currentTimeMillis();
	}

	public long getEndTime() {
		if (endTime == 0) {
			setEndTime();
		}
		return endTime;
	}

	public int getDuration() {
		return (int) (getEndTime() - getStartTime());
	}

	public GameState gameOver() {
		Set<Color> rowsLocked = new HashSet<>();
		for (PlayerSession playerSession : playerSessions) {
			if (playerSession.getScoreCard().getAmountOfPenalties() == 4) return GameState.PENALTIES;
			playerSession.getScoreCard().getRows().forEach((color, row) -> {
				if (row.isLocked()) {
					rowsLocked.add(color);
				}
			});
		}
		if (rowsLocked.size() >= 2) {
			return GameState.ROWS;
		}
		return GameState.RUNNING;
	}

	public BotSession getBotSession() {
		return (BotSession) playerSessions[0];
	}

	public PlayerSession getHumanSession() {
		return playerSessions[1];
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public PlayerSession getActivePlayerSession() {
		for (PlayerSession playerSession : playerSessions) {
			if (playerSession.isActivePlayer()) return playerSession;
		}
		return null;
	}

	public void save() {
		try (Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/qwixx",
				"postgres",
				"anubis512")) {
			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO game_session(duration, start_time, end_time) VALUES (?,?,?)
					""");
			statement.setInt(1, getDuration());
			statement.setTimestamp(2, new Timestamp(startTime));
			statement.setTimestamp(3, new Timestamp(endTime));
			statement.executeUpdate();

			final int totalBotScore = getBotSession().getScoreCard().getTotalScore();
			final int totalHumanScore = getHumanSession().getScoreCard().getTotalScore();
			final boolean playerWin = totalHumanScore > totalBotScore;
			for (PlayerSession playerSession : playerSessions) {
				playerSession.save(connection, (playerSession == getHumanSession()) == playerWin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isHumanSession(PlayerSession session) {
		return session.equals(getHumanSession());
	}
}
