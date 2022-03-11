package App.src.model;

public class ColoredDie extends Die{
	private final Color color;

	public ColoredDie(Color color) {
		super();
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
