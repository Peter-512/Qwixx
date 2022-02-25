package App.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class UI {
	public static String askForName() {
		System.out.print("Please enter your name: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static void printDieValues(DicePool pool) {
		System.out.println();
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

	public static void showOptions(Player player, DicePool dicePool) {
		HashMap<String, LinkedList<NumberField>> options = player.possibleNumbers(dicePool);
		System.out.println();
		for (Map.Entry<String, LinkedList<NumberField>> row : options.entrySet()) {
			System.out.printf("%s row - ", row.getKey());
			for (NumberField numberField : row.getValue()) {
				System.out.printf("%d\n", numberField.getValue());
			}
		}
	}
}
