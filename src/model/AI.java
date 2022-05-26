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
		final NumberField numberField = findMinDistNumberField(numberFields);
		int minDist = numberField.getRow().getAmountOfNumberFieldsBefore(numberField.getIndex());
		// if min bigger than 2, return null
		// else return numberField
		if (getScoreCard().getAmountOfPenalties() < 3 && minDist < 3) {
			Row row = numberField.getRow();
			row.disableNumberFieldsBefore(numberField.getIndex());
			if (numberField.isLast()) {
				row.setLocked();
				
			}
			return numberField;
		} else return null;
	}

	@Override
	NumberField choosePublicNumber(int total) {
		final ArrayList<NumberField> publicNumberFields = getScoreCard().getPublicNumberFields(total);
		if (publicNumberFields.isEmpty()) {
			return null;
		}
		final NumberField numberField = findMinDistNumberField(publicNumberFields);
		int minDist = numberField.getRow().getAmountOfNumberFieldsBefore(numberField.getIndex());
		// if min bigger than 2, return null
		// else return numberField
		if (minDist < 3) {
			Row row = numberField.getRow();
			row.disableNumberFieldsBefore(numberField.getIndex());
			if (numberField.isLast()) {
				row.setLocked();
			}
			return numberField;
		}
		return null;
	}

	private NumberField findMinDistNumberField(ArrayList<NumberField> numberFields) {
		// compute distance to previous crossed out nf and save in variable min
		NumberField numberField = numberFields.get(0);
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
		return numberField;
	}

}
