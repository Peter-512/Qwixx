package App.src;

import java.util.ArrayList;

public class DicePool {
	ArrayList<Die> dicePool = new ArrayList<>();

	public DicePool() {
		String[] colors = { "red", "yellow", "green", "blue", "white", "white" };
		for (String color : colors) {
			dicePool.add(new Die(color));
		}
	}
}
