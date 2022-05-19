package src.model;

import java.util.ArrayList;

public class AI extends BotSession {

	public AI(String name, boolean startingPlayer) {
		super(name, startingPlayer);
	}

	@Override
	NumberField chooseColoredNumber(DicePool coloredDicePool, DicePool publicDicePool) {
		final ArrayList<NumberField> numberFields = getScoreCard().getColoredNumberFields(coloredDicePool, publicDicePool);
		if (numberFields.isEmpty()) {
			return null;
		}
		NumberField numberField = numberFields.get(0);
		// compute distance to previous crossed out nf and save in variable min
		int minDist = numberField.getRow().getAmountOfNumberFieldsBefore(numberField.getIndex());
		for (NumberField nf : numberFields) {
			// compute distance to previous crossed out nf
			int dist = nf.getRow().getAmountOfNumberFieldsBefore(nf.getIndex());
			// check if it's lower than min
			if (dist < minDist) {
				// if it is, set numberField to nf
				minDist = dist;
				numberField = nf;
			}
		}
		// if min bigger than 2, return null
		// else return numberField
		if (getScoreCard().getAmountOfPenalties() < 3 && minDist < 3) {
			numberField.getRow().disableNumberFieldsBefore(numberField.getIndex());
			return numberField;
		} else return null;
	}

	@Override
	NumberField choosePublicNumber(int total) {
		final ArrayList<NumberField> publicNumberFields = getScoreCard().getPublicNumberFields(total);
		if (publicNumberFields.isEmpty()) {
			return null;
		}
		NumberField numberField = publicNumberFields.get(0);
		// compute distance to previous crossed out nf and save in variable min
		int minDist = numberField.getRow().getAmountOfNumberFieldsBefore(numberField.getIndex());
		for (NumberField nf : publicNumberFields) {
			// compute distance to previous crossed out nf
			int dist = nf.getRow().getAmountOfNumberFieldsBefore(nf.getIndex());
			// check if it's lower than min
			if (dist < minDist) {
				// if it is, set numberField to nf
				minDist = dist;
				numberField = nf;
			}
		}
		// if min bigger than 2, return null
		// else return numberField
		if (minDist < 3) {
			numberField.getRow().disableNumberFieldsBefore(numberField.getIndex());
			return numberField;
		}
		return null;
	}

}
