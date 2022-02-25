package App.src;

public class Player {
	private String name;
	private int amountOfPenalties;
	private Row[] rows = new Row[4];
	Score score;


	public Player(String name) {
		this.name = name;
		amountOfPenalties = 0;
		score = new Score();

		String[] colors = { "red", "yellow", "green", "blue" };
		boolean[] ascendingOrder = { true, true, false, false };

		for (int i = 0; i < 4; i++) {
			rows[i] = new Row(colors[i], ascendingOrder[i]);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountOfPenalties() {
		return amountOfPenalties;
	}

	public void incrementPenalties() {
		amountOfPenalties++;
	}

	public Row[] getRows() {
		return rows;
	}

	public void getTotalPoints() {
		int totalPoints = 0;
		for (Row row : rows) {
			totalPoints += row.getRowScore();
		}
	}

	//	public int getRowScore(String[] colors, Row[] rows, Score points){
	//		return rowScore;
	//	}
}
