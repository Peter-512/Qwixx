package App.src.model;

public class Score {
	public int points;
	private int totalAmountOfCrosses;
	private int totalScore;
	private int penaltyPoints;
	private ScoreCard scoreCard;

	public Score(ScoreCard scoreCard) {
		points = 0;
		totalAmountOfCrosses = 0;
		totalScore = 0;
		penaltyPoints = 0;
		this.scoreCard = scoreCard;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
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
		return scoreCard.getAmountOfPenalties() * 5;
	}

}
