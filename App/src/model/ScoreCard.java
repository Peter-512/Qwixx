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

	public int getAmountOfPenalties() {
		return amountOfPenalties;
	}

	public void addPenalty() {
		amountOfPenalties++;
	}

	public Row getRow(Color color) {
		return rows.get(color);
	}
}
