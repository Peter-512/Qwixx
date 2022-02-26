package App.src;

import java.util.LinkedHashMap;

public class Row {
	private Color color;
	private boolean isAscending;
	private boolean isLocked;
	private int rowScore;
	private final LinkedHashMap<Integer, NumberField> numberFields = new LinkedHashMap<>();

	public Row(Color color, boolean isAscending) {
		this.color = color;
		this.isAscending = isAscending;
		this.isLocked = false;
		this.rowScore = 0;

		if (isAscending) {
			for (int i = 2; i < 13; i++) {
				numberFields.put(i, new NumberField(i));
			}
		} else {
			for (int i = 12; i > 1; i--) {
				numberFields.put(i, new NumberField(i));
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
