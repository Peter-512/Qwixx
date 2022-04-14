package App.src.model;

import java.util.LinkedList;

public class GameSession {
	private PlayerSession[] playerSessions;
	private DicePool coloredDicePool;
	private DicePool publicDicePool;
	private LinkedList<Turn> turns;
	private boolean isHumanActivePlayer;
	private long startTime;
	private long endTime;

	public GameSession(String name, boolean startingPlayer) {
		playerSessions = new PlayerSession[2];
		playerSessions[0] = new PlayerSession(name, startingPlayer);
		playerSessions[1] = new PlayerSession("Skynet", !startingPlayer);
		coloredDicePool = new DicePool();
		publicDicePool = new DicePool(true);
		turns = new LinkedList<>();
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

	public LinkedList<Turn> getTurns() {
		return turns;
	}

	public void newTurn() {
		turns.add(new Turn(turns.size() + 1));
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

	public PlayerSession getActivePlayerSession() {
		for (PlayerSession playerSession : playerSessions) {
			if (playerSession.isActivePlayer()) return playerSession;
		}
		return null;
	}

	public void addAction() {
		for (PlayerSession playerSession : playerSessions) {
			Action action = playerSession.getCurrentAction();
			if (!turns.getLast().getCurrentAction().equals(action))
				turns.getLast().addAction(action);
		}
	}
}
