package App.src.model;

import java.util.ArrayList;

public class DicePool {
	private ArrayList<Die> dice = new ArrayList<>();
	private final boolean isPublic;

	public DicePool() {
		for (Color color : Color.values()) {
			dice.add(new ColoredDie(color));
		}
		isPublic = false;
	}

	public DicePool(boolean isPublic) {
		for (int i = 0; i < 2; i++) {
			dice.add(new Die());
		}
		this.isPublic = isPublic;
	}

	public void remove(Die die) {
		dice.remove(die);
	}

	public ArrayList<Die> getDice() {
		return dice;
	}

	public Die getDie(int index) {
		return dice.get(index);
	}

	public void throwDice() {
		for (Die die : dice) {
			die.throwDie();
		}
	}

	public boolean isPublic() {
		return isPublic;
	}
}
