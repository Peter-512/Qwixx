package App.src.model;

import java.util.ArrayList;

public class Row {
	private final Color color;
	private final boolean isAscending;
	private boolean isLocked;
	private final ArrayList<NumberField> numberFields = new ArrayList<>();

	public Row(Color color, boolean isAscending) {
		this.color = color;
		this.isAscending = isAscending;
		this.isLocked = false;

		if (isAscending) {
			for (int i = 0; i < 11; i++) {
				numberFields.add(new NumberField(i + 2, i));
			}
		} else {
			int val = 12;
			for (int i = 0; i < 11; i++) {
				numberFields.add(new NumberField(val--, i));
			}
		}
	}

	public Color getColor() {
		return color;
	}

	public boolean isAscending() {
		return isAscending;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked() {
		isLocked = true;
	}

	public int getRowScore() {
		int amountOfCrosses = 0;
		int totalScore = 0;
		for (NumberField numberField : numberFields) {
			if (numberField.isCrossed()) {
				amountOfCrosses++;
				totalScore += amountOfCrosses;
			}
		}
		return totalScore;
	}

	public NumberField getNumberField(int index) {
		return numberFields.get(index);
	}

	public ArrayList<NumberField> getNumberFields() {
		return numberFields;
	}

	public NumberField getOption(int i) {
		if (!(numberFields.get(i).isDisabled() || numberFields.get(i).isCrossed()))
			return numberFields.get(i);
		else return null;
	}

	public void disableNumberField(int index) {
		for (int i = 0; i < index; i++) {
			if (!numberFields.get(i).isCrossed()) {
				numberFields.get(i).setDisabled();
			}
		}
	}

	public int getAmountOfCrossedNumbers() {
		int total = 0;
		for (NumberField numberField : numberFields) {
			if (numberField.isCrossed()) {
				total++;
			}
		}
		return total;
	}
}
