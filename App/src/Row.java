package App.src;

public class Row {
	private String color;
	private boolean isAscending;
	private boolean isLocked;
	private int rowScore;
	private final NumberField[] numberFields = new NumberField[11];

	public Row(String color, boolean isAscending) {
		this.color = color;
		this.isAscending = isAscending;
		this.isLocked = false;
		this.rowScore = 0;

		if (isAscending) {
			int index = 0;
			for (int i = 2; i < 13; i++) {
				numberFields[index++] = new NumberField(i);
			}
		} else {
			int index = 0;
			for (int i = 12; i > 1; i--) {
				numberFields[index++] = new NumberField(i);
			}
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isAscending() {
		return isAscending;
	}

	public void setAscending(boolean ascending) {
		isAscending = ascending;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}

	public int getRowScore() {
		return rowScore;
	}

	public void setRowScore(int rowScore) {
		this.rowScore = rowScore;
	}

	public NumberField[] getNumberFields() {
		return numberFields;
	}
}
