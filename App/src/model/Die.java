package App.src.model;

import java.util.Random;

public class Die {
	private int value;
	private final Random random;
	private final int MAX_DIE_VALUE = 6;

	public Die() {
		this.random = new Random();
	}

	public void throwDie() {
		value = random.nextInt(MAX_DIE_VALUE) + 1;
	}

	public int getValue() {
		return value;
	}
}
