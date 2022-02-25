
public class Player extends Score{
	private String name;
	private int amountOfPenalties;
	private Row[] rows = new Row[4];


	public Player(String name) {
		this.name = name;
		amountOfPenalties = 0;
		Score storage = new Score();
		Row rowValue = new Row();

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

	public void setRows(Row[] rows) {
		this.rows = rows;
	}

	public int getPoints() {
		return points;
	}
	
//	public int getRowScore(String[] colors, Row[] rows, Score points){
//		return rowScore;
//	}
}
