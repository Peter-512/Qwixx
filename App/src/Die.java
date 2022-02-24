package App.src;

import java.util.Random;

public class Die {
	private final String color;
	private int value;
	private Random random;
	private final int MAX_DIE_VALUE = 6;

	public Die(String color) {
		this.color = color;
		this.random = new Random();
	}

	public String getColor() {
		return color;
	}

	public int throwDie() {
		return random.nextInt(MAX_DIE_VALUE) + 1;
	}

}
