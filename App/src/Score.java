package App.src;

public class Score {
	private int points;
	private int totalDuration;
	private int totalAmountOfCrosses;
	private int totalScore;
	private int penaltyPoints;

	public Score() {
		points = 0;
		totalDuration = 0;
		totalAmountOfCrosses = 0;
		totalScore = 0;
		penaltyPoints = 0;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public int getTotalAmountOfCrosses() {
		return totalAmountOfCrosses;
	}

	public void setTotalAmountOfCrosses(int totalAmountOfCrosses) {
		this.totalAmountOfCrosses = totalAmountOfCrosses;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}
}
