package App.src.model;

import java.util.Scanner;

public class UI {
	public static String askForName() {
		System.out.print("Please enter your name: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static void printDieValues(DicePool pool) {
		if (pool.isPublic()) {
			for (Die die : pool.getDice()) {
				System.out.printf("%s die - %d\n", "WHITE", die.getValue());
			}
		} else {
			for (Die die : pool.getDice()) {
				ColoredDie coloredDie = (ColoredDie) die;
				System.out.printf("%s die - %d\n", coloredDie.getColor(), coloredDie.getValue());
			}
		}
	}

	public static void printOptions(Color color, NumberField numberField) {
		System.out.printf("%s row - value: %d\n", color, numberField.getValue());
	}

}
