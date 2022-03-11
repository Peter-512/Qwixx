package App.src.model;

import java.util.ArrayList;

public class DicePool {
	private ArrayList<ColoredDie> coloredDice = new ArrayList<>();
	private Die[] publicDice = new Die[2];


	public DicePool() {
		String[] colors = { "red", "yellow", "green", "blue" };
		for (Color color : Color.values()) {
			coloredDice.add(new ColoredDie(color));
		}

		for (int i = 0; i < 2; i++) {
			publicDice[i] = new Die();
		}
	}

	public ArrayList<ColoredDie> getColoredDice() {
		return coloredDice;
	}

	public Die[] getPublicDice() {
		return publicDice;
	}

	public void removeDie(Color color) {
		ColoredDie toBeRemoved = null;
		for (ColoredDie die : coloredDice) {
			if (color.equals(die.getColor())) {
				toBeRemoved = die;
			}
		}
		coloredDice.remove(toBeRemoved);
	}

	public void throwAllDice() {
		for (Die die : coloredDice) {
			die.throwDie();
		}
		for (Die die : publicDice) {
			die.throwDie();
		}
	}

	public int getPublicValue() {
		int total = 0;
		for (Die die : publicDice) {
			total += die.getValue();
		}
		return total;
	}
}
