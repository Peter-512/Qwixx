package App.src;

import java.util.Scanner;

public class UI {
	public static String askForName() {
		System.out.print("Please enter your name: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static void printDieValues(DicePool pool) {
		for (Die die : pool.getColoredDice()) {
			System.out.printf("%6s die - %d\n", die.getColor(), die.getValue());
		}
		for (Die die : pool.getPublicDice()) {
			System.out.printf("%6s die - %d\n", die.getColor(), die.getValue());
		}
	}

	public static void printScoreCard(Player player) {
		System.out.println();
		System.out.printf("%s's Scorecard\n", player.getName());
		for (Row row : player.getRows()) {
			System.out.printf("%10s row: ", row.getColor());
			for (NumberField numberField : row.getNumberFields()) {
				if (!numberField.isDisabled()) {
					if (numberField.isCrossed()) {
						System.out.print("X ");
					} else {
						System.out.printf("%d ", numberField.getValue());
					}
				}
			}
			System.out.println();
		}
	}
}
