package App.src;

import java.util.HashMap;
import java.util.LinkedList;

public class Player {
	private String name;
	private int amountOfPenalties;
	private Row[] rows = new Row[4];
	Score score;


	public Player(String name) {
		this.name = name;
		amountOfPenalties = 0;
		score = new Score();

		String[] colors = { "red", "yellow", "green", "blue" };
		boolean[] ascendingOrder = { true, true, false, false };

		for (int i = 0; i < 4; i++) {
			rows[i] = new Row(colors[i], ascendingOrder[i]);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountOfPenalties() {
		return amountOfPenalties;
	}

	public void incrementPenalties() {
		amountOfPenalties++;
	}

	public Row[] getRows() {
		return rows;
	}

	public Score getScore() {
		return score;
	}

	public int getTotalPoints() {
		return score.getTotalScore();
	}

	public HashMap<String, LinkedList<NumberField>> possibleNumbers(DicePool dicePool) {
		HashMap<String, LinkedList<NumberField>> options = new HashMap<>();
		int publicValue = dicePool.getPublicValue();
		for (Row row : rows) {
			if (!options.containsKey(row.getColor())) {
				options.put(row.getColor(), new LinkedList<>());
			}
			options.get(row.getColor()).add(row.getNumberField(publicValue));
		}
		return options;
	}
}
