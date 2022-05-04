package App.src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BotSession extends PlayerSession {
//	AI ai;

	public BotSession(String name, boolean startingPlayer) {

		super(name, startingPlayer);
	}

	private NumberField chooseColoredNumber(DicePool coloredDicePool, DicePool publicDicePool) {
		Random random = new Random();
		final HashMap<Color, ArrayList<NumberField>> coloredNumberFields = getScoreCard().getColoredNumberFields(coloredDicePool, publicDicePool);
		if (!coloredNumberFields.keySet().isEmpty()) {
			final Color color = (Color) coloredNumberFields.keySet()
			                                               .toArray()[random.nextInt(coloredNumberFields.keySet()
			                                                                                            .toArray().length)];
			final ArrayList<NumberField> numberFields = coloredNumberFields.get(color);
			NumberField numberField = numberFields.get(random.nextInt(numberFields.size()));
			getScoreCard().getRow(color).disableNumberFieldsUntil(numberField.getIndex());
			return numberField;
		}
		return null;
	}


	private NumberField choosePublicNumber(int total) {
		Random random = new Random();
		final HashMap<Color, NumberField> publicNumberFields = getScoreCard().getPublicNumberFields(total);
		if (!publicNumberFields.keySet().isEmpty()) {
			final Color color = (Color) publicNumberFields.keySet().toArray()[random.nextInt(publicNumberFields.keySet()
			                                                                                       .toArray().length)];
			NumberField numberField = publicNumberFields.get(color);
			getScoreCard().getRow(color).disableNumberFieldsUntil(numberField.getIndex());
			return numberField;
		}
		return null;
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
				}
			}
		}
	}

}
