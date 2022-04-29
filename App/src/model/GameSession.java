package App.src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GameSession {
	private PlayerSession[] playerSessions;
	private DicePool coloredDicePool;
	private DicePool publicDicePool;
	private boolean isHumanActivePlayer;
	private long startTime;
	private long endTime;

	public GameSession(String name, boolean startingPlayer) {
		playerSessions = new PlayerSession[2];
		playerSessions[0] = new BotSession("Skynet", !startingPlayer);
		playerSessions[1] = new PlayerSession(name, startingPlayer);
		coloredDicePool = new DicePool();
		publicDicePool = new DicePool(true);
		isHumanActivePlayer = startingPlayer;
		startTime = System.currentTimeMillis();
		endTime = System.currentTimeMillis();
	}

	public void throwAllDice() {
		coloredDicePool.throwDice();
		publicDicePool.throwDice();
	}

	public void removeDie(Color color) {
		for (Die die : coloredDicePool.getDice()) {
			ColoredDie coloredDie = (ColoredDie) die;
			if (color.equals(coloredDie.getColor())) {
				coloredDicePool.remove(coloredDie);
				return;
			}
		}
	}

	public int totalPublicThrow() {
		return publicDicePool.getDice().get(0).getValue() + publicDicePool.getDice().get(1).getValue();
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

	public long getEndTime() {
		return endTime = System.currentTimeMillis() - startTime;
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

	public void storeGameSession(int duration, int game_ID) {
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/qwixx",
					"postgres",
					"Student_1234");
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO game_session values (?,default)");
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
    public void save(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/qwixx",
                    "postgres",
                    "Student_1234");
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO game_session values (duration,default)");
            for (PlayerSession playerSession : playerSessions) {
                playerSession.save(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

		public boolean isHumanSession (PlayerSession session){
			return session.equals(getHumanSession());
		}
	}
