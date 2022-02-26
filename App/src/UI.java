package App.src;

import java.util.Scanner;

/**
 This class is only for testing and Terminal output purposes for the time being
 */
public class UI {
	static Scanner scanner = new Scanner(System.in);

	public static String askForName() {
		System.out.print("Please enter your name: ");
		return scanner.nextLine();
	}

	public static void printDieValues(DicePool pool) {
		System.out.println();
		for (ColoredDie die : pool.getColoredDice()) {
			System.out.printf("%6s die - %d\n", die.getColor(), die.getValue());
		}
		for (Die die : pool.getPublicDice()) {
			System.out.printf("%6s die - %d\n", "white", die.getValue());
		}
	}

	public static void printScoreCard(Player player) {
		System.out.println();
		System.out.printf("%s's Scorecard\n", player.getName());
		for (Row row : player.getRows().values()) {
			System.out.printf("%10s row: ", row.getColor());
			for (NumberField numberField : row.getNumberFields().values()) {
				if (!numberField.isDisabled()) {
					if (numberField.isCrossed()) {
						System.out.print(" X ");
					} else {
						System.out.printf("%2d ", numberField.getValue());
					}
				}
			}
			System.out.println();
		}
	}

	public static void printOptions(Color color, NumberField numberField) {
		System.out.printf("%s row - value: %d\n", color, numberField.getValue());
	}

	public static int askForNumber() {
		System.out.print("Choose an index: ");
		return scanner.nextInt();
	}

}
