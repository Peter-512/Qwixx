package App.src;

import java.util.ArrayList;

public class DicePool {
	private ArrayList<Die> coloredDice = new ArrayList<>();
	private Die[] publicDice = new Die[2];

	public DicePool() {
		String[] colors = { "red", "yellow", "green", "blue" };
		for (String color : colors) {
			coloredDice.add(new Die(color));
		}

		for (int i = 0; i < 2; i++) {
			publicDice[i] = new Die("white");
		}
	}

	public ArrayList<Die> getColoredDice() {
		return coloredDice;
	}

	public Die[] getPublicDice() {
		return publicDice;
	}

	public void removeDie(String color) {
		Die toBeRemoved = null;
		for (Die die : coloredDice) {
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
}
