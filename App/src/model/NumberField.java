package App.src.model;

public class NumberField {
	private int value;
	private boolean isCrossed;
	private boolean isDisabled;

	public NumberField(int value) {
		this.value = value;
		this.isCrossed = false;
		this.isDisabled = false;
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
}
