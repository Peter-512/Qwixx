package App.src;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Player {
	private String name;
	private int amountOfPenalties;
	private final LinkedHashMap<Color, Row> rows = new LinkedHashMap<>();
	Score score;


	public Player(String name) {
		this.name = name;
		amountOfPenalties = 0;
		score = new Score();

		for (Color color : Color.values()) {
			rows.put(color, new Row(color, color.ordinal() > 1));
			System.out.println(color.ordinal());
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

	public LinkedHashMap<Color, Row> getRows() {
		return rows;
	}

	public Score getScore() {
		return score;
	}

	public int getTotalPoints() {
		return score.getTotalScore();
	}

	public void getPossiblePublicNumbers(DicePool dicePool) {
		int publicValue = dicePool.getPublicValue();
		for (Row row : rows.values()) {
			UI.printOptions(row.getColor(), row.getOption(publicValue));
		}
	}

	public void getPossibleColoredNumbers(DicePool dicePool) {
		ArrayList<ColoredDie> coloredDice = dicePool.getColoredDice();
		Die[] publicDice = dicePool.getPublicDice();
		for (ColoredDie colDie : coloredDice) {
			int colDieVal = colDie.getValue();
			for (Die pubDie : publicDice) {
				int total = colDieVal + pubDie.getValue();
				Row row = rows.get(colDie.getColor());
				UI.printOptions(colDie.getColor(), row.getNumberField(total));
			}
		}
	}

}
