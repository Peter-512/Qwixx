package App.src;

import java.util.ArrayList;

public class DicePool {
	ArrayList<Die> dicePool = new ArrayList<>();
	Die[] publicDice = new Die[2];

	public DicePool() {
		String[] colors = { "red", "yellow", "green", "blue" };
		for (String color : colors) {
			dicePool.add(new Die(color));
		}

		for (int i = 0; i < 2; i++) {
			publicDice[i] = new Die("white");
		}
	}
}
