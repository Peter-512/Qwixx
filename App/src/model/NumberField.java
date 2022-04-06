package App.src.model;

public class NumberField {
	private final int value;
	private boolean isCrossed;
	private boolean isDisabled;
	private final int index;

	public NumberField(int value, int index) {
		this.value = value;
		this.isCrossed = false;
		this.isDisabled = false;
		this.index = index;
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
