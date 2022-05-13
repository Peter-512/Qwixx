package src.model;

import java.util.ArrayList;
import java.util.Random;

public class BotSession extends PlayerSession {
	//	AI ai;

	public BotSession(String name, boolean startingPlayer) {
		super(name, startingPlayer);
	}

	private NumberField chooseColoredNumber(DicePool coloredDicePool, DicePool publicDicePool) {
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

	private NumberField choosePublicNumber(int total) {
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
			numberField.setCrossed();
			//			TODO pass correct arguments
			takeAction(0, 0, 0);
		} else {
			passAction();
		}
		if (isActivePlayer()) {
			numberField = chooseColoredNumber(coloredDicePool, publicDicePool);
			if (numberField != null) {
				numberField.setCrossed();
				//				TODO pass correct arguments
				takeAction(0, 0, 0);
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
