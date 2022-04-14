package App.src.model;

public class Action {
	int amountOfNumbersCrossed;
	int amountOfNumbersMissed;
	int pointsEarned;
	boolean passedTurn;

	public Action(int amountOfNumbersCrossed, int amountOfNumbersMissed, int pointsEarned) {
		this.amountOfNumbersCrossed = amountOfNumbersCrossed;
		this.amountOfNumbersMissed = amountOfNumbersMissed;
		this.pointsEarned = pointsEarned;
		passedTurn = false;
	}

	public Action() {
		this(0, 0, 0);
		passedTurn = true;
	}
}
