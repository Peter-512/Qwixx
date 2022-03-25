package App.src.model;

import java.util.LinkedList;

public class GameSession {
	private Player player;
	private PlayerSession playerSession;
	private Player cpu;
	private PlayerSession cpuSession;
	private DicePool coloredDicePool;
	private DicePool publicDicePool;
	private LinkedList<Turn> turns;

	public GameSession(String name) {
		player = new Player(name);
		cpu = new Player("Skynet");
		playerSession = new PlayerSession();
		cpuSession = new PlayerSession();
		coloredDicePool = new DicePool();
		publicDicePool = new DicePool(true);
		turns = new LinkedList<>();
	}

	public void startGame() {
		throwAllDice();
		getPossibleColoredNumbers();
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

	public void getPossibleColoredNumbers() {
		for (Die die : coloredDicePool.getDice()) {
			ColoredDie colDie = (ColoredDie) die;
			int colDieVal = colDie.getValue();
			for (Die pubDie : publicDicePool.getDice()) {
				int total = colDieVal + pubDie.getValue();
				Row row = playerSession.getScoreCard().getRow(colDie.getColor());
			}
		}
	}

	public LinkedList<Turn> getTurns() {
		return turns;
	}

	public void newTurn() {
		turns.add(new Turn(turns.size() + 1));
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerSession getPlayerSession() {
		return playerSession;
	}

	public Player getCpu() {
		return cpu;
	}

	public PlayerSession getCpuSession() {
		return cpuSession;
	}

	public DicePool getColoredDicePool() {
		return coloredDicePool;
	}

	public DicePool getPublicDicePool() {
		return publicDicePool;
	}
}
