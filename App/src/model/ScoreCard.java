package App.src.model;

import java.util.LinkedHashMap;

public class ScoreCard {
	private int amountOfPenalties;
	private final LinkedHashMap<Color, Row> rows;


	public ScoreCard() {
		amountOfPenalties = 0;
		rows = new LinkedHashMap<>();

		for (Color color : Color.values()) {
			rows.put(color, new Row(color, color.ordinal() > 1));
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


}
