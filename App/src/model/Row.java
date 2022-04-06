package App.src.model;

import java.util.ArrayList;

public class Row {
	private final Color color;
	private final boolean isAscending;
	private boolean isLocked;
	private int rowScore;
	private final ArrayList<NumberField> numberFields = new ArrayList<>();

	public Row(Color color, boolean isAscending) {
		this.color = color;
		this.isAscending = isAscending;
		this.isLocked = false;
		this.rowScore = 0;

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
		return rowScore;
	}

	public void setRowScore() {
		//TODO implement table for amount of points per cross
	}

	public NumberField getNumberField(int value) {
		return numberFields.get(value);
	}

	public ArrayList<NumberField> getNumberFields() {
		return numberFields;
	}

	public NumberField getOption(int value) {
		if (!numberFields.get(value).isDisabled())
			return numberFields.get(value);
		else return null;
	}

	public void disableNumberFields(int index) {
		for (int i = 0; i < index; i++) {
			if (!numberFields.get(i).isCrossed()) {
				numberFields.get(i).setDisabled();
			}
		}
	}
}
