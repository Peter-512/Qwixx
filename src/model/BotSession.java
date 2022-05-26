package src.model;

import java.util.ArrayList;
import java.util.Random;

public class BotSession extends PlayerSession {
	public BotSession(String name, boolean startingPlayer) {
		super(name, startingPlayer);
	}

	NumberField chooseColoredNumber(DicePool coloredDicePool, DicePool publicDicePool) {
		Random random = new Random();
		final ArrayList<NumberField> numberFields = getScoreCard().getColoredNumberFields(coloredDicePool, publicDicePool);
		if (numberFields.isEmpty()) {
			return null;
		}
		final NumberField numberField = numberFields.get(random.nextInt(numberFields.size()));
		numberField.getRow().disableNumberFieldsBefore(numberField.getIndex());
		if (numberField == numberField.getRow().getLast()) {
			numberField.getRow().setLocked();
		}
		return numberField;
	}

	NumberField choosePublicNumber(int total) {
		Random random = new Random();
		final ArrayList<NumberField> publicNumberFields = getScoreCard().getPublicNumberFields(total);
		if (publicNumberFields.isEmpty()) {
			return null;
		}
		NumberField numberField = publicNumberFields.get(random.nextInt(publicNumberFields.size()));
		numberField.getRow().disableNumberFieldsBefore(numberField.getIndex());
		if (numberField == numberField.getRow().getLast()) {
			numberField.getRow().setLocked();
		}
		return numberField;
	}

	public void takeTurn(int total, DicePool coloredDicePool, DicePool publicDicePool) {
		NumberField numberField = choosePublicNumber(total);
		if (numberField != null) {
			int rowScoreBefore = numberField.getRow().getRowScore();
			int numbersMissed = numberField.getRow().getAmountOfNumberFieldsBefore(numberField.getIndex());
			numberField.setCrossed();
			int rowScoreAfter = numberField.getRow().getRowScore();
			takeAction(numbersMissed, rowScoreAfter - rowScoreBefore);
		} else {
			passAction();
		}
		if (isActivePlayer()) {
			numberField = chooseColoredNumber(coloredDicePool, publicDicePool);
			if (numberField != null) {
				int rowScoreBefore = numberField.getRow().getRowScore();
				int numbersMissed = numberField.getRow().getAmountOfNumberFieldsBefore(numberField.getIndex());
				numberField.setCrossed();
				int rowScoreAfter = numberField.getRow().getRowScore();
				takeAction(numbersMissed, rowScoreAfter - rowScoreBefore);
			} else {
				if (getCurrentTurn().getLastAction().isPassedTurn()) {
					takePenaltyAction();
				} else {
					passAction();
				}
			}
		}
	}
}
