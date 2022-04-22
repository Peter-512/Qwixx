package App.src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ScoreCard {
	private int amountOfPenalties;
	private final LinkedHashMap<Color, Row> rows;
	private PlayerSession playerSession;


	public ScoreCard(PlayerSession playerSession) {
		amountOfPenalties = 0;
		rows = new LinkedHashMap<>();
		this.playerSession = playerSession;

		for (Color color : Color.values()) {
			rows.put(color, new Row(color, color.ordinal() < 2));
		}
	}

	public int getAmountOfLockedRows() {
		int total = 0;
		for (Color color : Color.values()) {
			if (rows.get(color).isLocked()) total++;
		}
		return total;
	}

	public int getAmountOfPenalties() {
		return amountOfPenalties;
	}

	public void addPenalty() {
		if (amountOfPenalties < 4)
			amountOfPenalties++;
	}

	public Row getRow(Color color) {
		return rows.get(color);
	}

	public LinkedHashMap<Color, Row> getRows() {
		return rows;
	}

	public PlayerSession getPlayerSession() {
		return playerSession;
	}

	public HashMap<Color, NumberField> getPublicNumberFields(int total) {
		HashMap<Color, NumberField> map = new HashMap<>();
		for (Color color : Color.values()) {
			Row row = getRow(color);
			if (!row.isLocked()) {
				row.getNumberFields().forEach(numberField -> {
					if (numberField.getValue() == total && !numberField.isDisabled() && !numberField.isCrossed()) {
						if (color == Color.RED || color == Color.YELLOW) {
							if (numberField.getValue() < 12 || row.getAmountOfCrossedNumbers() >= 5) {
								map.put(color, numberField);
							}
						} else {
							if (numberField.getValue() > 2 || row.getAmountOfCrossedNumbers() >= 5) {
								map.put(color, numberField);
							}
						}
					}
				});
			}
		}
		return map;
	}

	public HashMap<Color, ArrayList<NumberField>> getColoredNumberFields(DicePool coloredDicePool, DicePool publicDicePool) {
		HashMap<Color, ArrayList<NumberField>> map = new HashMap<>();
		for (Die die : coloredDicePool.getDice()) {
			ColoredDie colDie = (ColoredDie) die;
			int colDieVal = colDie.getValue();
			for (Die pubDie : publicDicePool.getDice()) {
				int total = colDieVal + pubDie.getValue();
				final Color color = colDie.getColor();
				Row row = getRow(color);
				if (!row.isLocked()) {
					row.getNumberFields().forEach(numberField -> {
						if (numberField.getValue() == total && !numberField.isDisabled() && !numberField.isCrossed()) {
							if (color == Color.RED || color == Color.YELLOW) {
								if (numberField.getValue() < 12 || row.getAmountOfCrossedNumbers() >= 5) {
									map.computeIfAbsent(color, k -> new ArrayList<>());
									map.get(color).add(numberField);
								}
							} else {
								if (numberField.getValue() > 2 || row.getAmountOfCrossedNumbers() >= 5) {
									map.computeIfAbsent(color, k -> new ArrayList<>());
									map.get(color).add(numberField);
								}
							}
						}
					});
				}
			}
		}
		return map;
	}

	public int getTotalPoints() {
		int total = 0;
		for (Color color : Color.values()) {
			total += rows.get(color).getRowScore();
		}
		return total - getTotalPenaltyPoints();
	}

	public int getTotalAmountOfNumbersCrossed() {
		int total = 0;
		for (Color color : Color.values()) {
			total += rows.get(color).getAmountOfCrossedNumbers();
		}
		return total;
	}

	public int getTotalPenaltyPoints() {
		return amountOfPenalties * 5;
	}

}
