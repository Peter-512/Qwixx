package App.src.model;

public class NumberField {
	private final int value;
	private boolean isCrossed;
	private boolean isDisabled;
	private final int index;
	private final Row row;

	public NumberField(int value, int index, Row row) {
		this.value = value;
		this.row = row;
		this.isCrossed = false;
		this.isDisabled = false;
		this.index = index;
	}

	public boolean isAvailable() {
		return !(isDisabled() || isCrossed());
	}

	public Row getRow() {
		return row;
	}

	public int getValue() {
		return value;
	}

	public boolean isCrossed() {
		return isCrossed;
	}

	public void setCrossed() {
		isCrossed = true;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled() {
		isDisabled = true;
	}

	public int getIndex() {
		return index;
	}

}
