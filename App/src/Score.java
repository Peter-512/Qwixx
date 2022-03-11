package App.src;

public class Score {
	public int points;
	private int totalDuration;
	private int totalAmountOfCrosses;
	private int totalScore;
	private int penalties;

	public Score() {
		points = 0;
		totalDuration = 0;
		totalAmountOfCrosses = 0;
		totalScore = 0;
		penalties = 0;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void addTotalDuration(int duration) {
		this.totalDuration += duration;
	}

	public int getTotalAmountOfCrosses() {
		return totalAmountOfCrosses;
	}

	public void incrementTotalAmountOfCrosses() {
		totalAmountOfCrosses++;
	}

	public int getTotalScore() {
		return getPoints() - getPenaltyPoints();
	}

	public int getPenaltyPoints() {
		return penalties * 5;
	}

	public void addPenalty() {
		penalties++;
	}
}
