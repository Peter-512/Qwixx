package App.src.model;

import java.util.HashMap;
import java.util.LinkedList;

public class GameSession {
	private PlayerSession playerSession;
	private PlayerSession cpuSession;
	private DicePool coloredDicePool;
	private DicePool publicDicePool;
	private LinkedList<Turn> turns;

	public GameSession(String name) {
		playerSession = new PlayerSession(name);
		cpuSession = new PlayerSession("Skynet");
		coloredDicePool = new DicePool();
		publicDicePool = new DicePool(true);
		turns = new LinkedList<>();
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

	public HashMap<Color, NumberField> getPublicNumberFields() {
		HashMap<Color, NumberField> map = new HashMap<>();
		int total = publicDicePool.getDice().get(0).getValue() + publicDicePool.getDice().get(1).getValue();
		for (Color color : Color.values()) {
			Row row = playerSession.getScoreCard().getRow(color);
			if (!row.isLocked()) {
				row.getNumberFields().forEach(numberField -> {
					if (numberField.getValue() == total && !numberField.isDisabled() && !numberField.isCrossed()) {
						map.put(color, numberField);
					}
				});
			}
		}
		return map;
	}

	public LinkedList<Turn> getTurns() {
		return turns;
	}

	public void newTurn() {
		turns.add(new Turn(turns.size() + 1));
	}

	public PlayerSession getPlayerSession() {
		return playerSession;
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

	public boolean isRunning() {
		if (playerSession.getScoreCard().getAmountOfPenalties() == 4) return false;
		if (cpuSession.getScoreCard().getAmountOfPenalties() == 4) return false;
		int totalRowsLocked = 0;
		totalRowsLocked += playerSession.getScoreCard().getAmountOfLockedRows();
		totalRowsLocked += cpuSession.getScoreCard().getAmountOfLockedRows();
		if (totalRowsLocked >= 2) {
			return false;
		}
		return true;
	}
}
