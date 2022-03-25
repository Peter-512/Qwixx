package App.src.model;

import java.util.LinkedHashMap;

public class Row {
	private final Color color;
	private final boolean isAscending;
	private boolean isLocked;
	private int rowScore;
	private final LinkedHashMap<Integer, NumberField> numberFields = new LinkedHashMap<>();

	public Row(Color color, boolean isAscending) {
		this.color = color;
		this.isAscending = isAscending;
		this.isLocked = false;
		this.rowScore = 0;

		if (isAscending) {
			for (int i = 0; i < 11; i++) {
				numberFields.put(i, new NumberField(i+2));
			}
		} else {
			int val = 12;
			for (int i = 0; i < 11; i++) {
				numberFields.put(i, new NumberField(val--));
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

	public LinkedHashMap<Integer, NumberField> getNumberFields() {
		return numberFields;
	}

	public NumberField getOption(int value) {
		if (!numberFields.get(value).isDisabled())
			return numberFields.get(value);
		else return null;
	}
}
