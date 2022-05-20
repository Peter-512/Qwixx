package src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class GameSession {
	private PlayerSession[] playerSessions;
	private DicePool coloredDicePool;
	private DicePool publicDicePool;
	private boolean isHumanActivePlayer;
	private long startTime;
	private long endTime;

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
	}

	public void throwAllDice() {
		coloredDicePool.throwDice();
		publicDicePool.throwDice();
	}

	public void removeDie(Die die) {
		getColoredDicePool().remove(die);
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

	public boolean isHumanActivePlayer() {
		return isHumanActivePlayer;
	}

	public void changeActivePlayer() {
		isHumanActivePlayer = !isHumanActivePlayer;
		for (PlayerSession playerSession : playerSessions) {
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

	public boolean gameOver() {
		int totalRowsLocked = 0;
		for (PlayerSession playerSession : playerSessions) {
			if (playerSession.getScoreCard().getAmountOfPenalties() == 4) return true;
			totalRowsLocked += playerSession.getScoreCard().getAmountOfLockedRows();
		}
		if (totalRowsLocked >= 2) {
			return true;
		}
		return false;
	}

	public BotSession getBotSession() {
		return (BotSession) playerSessions[0];
	}

	public PlayerSession getHumanSession() {
		return playerSessions[1];
	}

	public PlayerSession getActivePlayerSession() {
		for (PlayerSession playerSession : playerSessions) {
			if (playerSession.isActivePlayer()) return playerSession;
		}
		return null;
	}

	public void save() {
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/qwixx",
					"postgres",
					"anubis512");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO game_session(duration) VALUES (?)");
			statement.setInt(1, getDuration());
			statement.execute();
			for (PlayerSession playerSession : playerSessions) {
				playerSession.save(connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isHumanSession(PlayerSession session) {
		return session.equals(getHumanSession());
	}
}
