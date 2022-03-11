package App.src.model;

import java.util.Scanner;

public class UI {
	public static String askForName() {
		System.out.print("Please enter your name: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static void printDieValues(DicePool pool) {
		for (Die die : pool.getColoredDice()) {
			System.out.printf("%s die - %d\n", die.getColor(), die.getValue());
		}
		for (Die die : pool.getPublicDice()) {
			System.out.printf("%s die - %d\n", die.getColor(), die.getValue());
		}
	}
}
