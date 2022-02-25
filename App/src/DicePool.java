package App.src;

import java.util.ArrayList;

public class DicePool {
	private ArrayList<Die> coloredDicePool = new ArrayList<>();
	private Die[] publicDice = new Die[2];

	public DicePool() {
		String[] colors = { "red", "yellow", "green", "blue" };
		for (String color : colors) {
			coloredDicePool.add(new Die(color));
		}

		for (int i = 0; i < 2; i++) {
			publicDice[i] = new Die("white");
		}
	}

	public ArrayList<Die> getColoredDicePool() {
		return coloredDicePool;
	}

	public Die[] getPublicDice() {
		return publicDice;
	}

	public void removeDie(String color) {
		Die toBeRemoved = null;
		for (Die die : coloredDicePool) {
			if (color.equals(die.getColor())) {
				toBeRemoved = die;
			}
		}
		coloredDicePool.remove(toBeRemoved);
	}

	public void throwAllDice() {
		for (Die die : coloredDicePool) {
			die.throwDie();
		}
	}
}
