package src.model;

import java.util.Random;

public enum Color {
	RED,
	YELLOW,
	GREEN,
	BLUE;

	public static Color random() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
