package App.src;

import java.util.Random;

public class Die {
	private final String color;
	private int value;
	private final Random random;
	private final int MAX_DIE_VALUE = 6;

	public Die(String color) {
		this.color = color;
		this.random = new Random();
	}

	public String getColor() {
		return color;
	}

	public void throwDie() {
		value = random.nextInt(MAX_DIE_VALUE) + 1;
	}

	public int getValue() {
		return value;
	}
}
//comment comment
