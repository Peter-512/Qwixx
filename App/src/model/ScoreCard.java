package App.src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ScoreCard {
	private int amountOfPenalties;
	private final LinkedHashMap<Color, Row> rows;


	public ScoreCard() {
		amountOfPenalties = 0;
		rows = new LinkedHashMap<>();

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
		amountOfPenalties++;
	}

	public Row getRow(Color color) {
		return rows.get(color);
	}

	public LinkedHashMap<Color, Row> getRows() {
		return rows;
	}

	public HashMap<Color, NumberField> getPublicNumberFields(int total) {
		HashMap<Color, NumberField> map = new HashMap<>();
		for (Color color : Color.values()) {
			Row row = getRow(color);
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

	public HashMap<Color, ArrayList<NumberField>> getColoredNumberFields(DicePool coloredDicePool, DicePool publicDicePool) {
		HashMap<Color, ArrayList<NumberField>> map = new HashMap<>();
		for (Die die : coloredDicePool.getDice()) {
			ColoredDie colDie = (ColoredDie) die;
			int colDieVal = colDie.getValue();
			for (Die pubDie : publicDicePool.getDice()) {
				int total = colDieVal + pubDie.getValue();
				Row row = getRow(colDie.getColor());
				if (!row.isLocked()) {
					row.getNumberFields().forEach(numberField -> {
						if (numberField.getValue() == total && !numberField.isDisabled() && !numberField.isCrossed()) {
							map.computeIfAbsent(colDie.getColor(), k -> new ArrayList<>());
							map.get(colDie.getColor()).add(numberField);
						}
					});
				}
			}
		}
		return map;
	}


}
