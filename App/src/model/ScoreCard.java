package App.src.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ScoreCard {
	private int amountOfPenalties;
	private final LinkedHashMap<Color, Row> rows;
	private final PlayerSession playerSession;


	public ScoreCard(PlayerSession playerSession) {
		amountOfPenalties = 0;
		rows = new LinkedHashMap<>();
		this.playerSession = playerSession;

		for (Color color : Color.values()) {
			rows.put(color, new Row(color, color.ordinal() < 2));
		}
	}

	public int getAmountOfLockedRows() {
		int total = 0;
		for (Color color : Color.values()) {
			if (rows.get(color).isLocked()) total++;
		}
		return total;
	}

	public int getAmountOfPenalties() {
		return amountOfPenalties;
	}

	public void addPenalty() {
		if (amountOfPenalties < 4)
			amountOfPenalties++;
	}

	public Row getRow(Color color) {
		return rows.get(color);
	}

	public LinkedHashMap<Color, Row> getRows() {
		return rows;
	}

	public PlayerSession getPlayerSession() {
		return playerSession;
	}

	public ArrayList<NumberField> getPublicNumberFields(int total) {
		ArrayList<NumberField> list = new ArrayList<>();
		for (Color color : Color.values()) {
			Row row = getRow(color);
			if (!row.isLocked()) {
				NumberField numberField = row.getNumberFieldByValue(total);
				if (numberField.isAvailable()) {
					if ((row.isAscending() ? numberField.getValue() < 12 : numberField.getValue() > 2) || row.getAmountOfCrossedNumbers() >= 5) {
						list.add(numberField);
					}
				}
			}
		}
		return list;
	}

	public ArrayList<NumberField> getColoredNumberFields(DicePool coloredDicePool, DicePool publicDicePool) {
		ArrayList<NumberField> list = new ArrayList<>();
		for (Die die : coloredDicePool.getDice()) {
			ColoredDie colDie = (ColoredDie) die;
			int colDieVal = colDie.getValue();
			for (Die pubDie : publicDicePool.getDice()) {
				int total = colDieVal + pubDie.getValue();
				final Color color = colDie.getColor();
				Row row = getRow(color);
				if (!row.isLocked()) {
					final NumberField numberField = row.getNumberFieldByValue(total);
					if (numberField.isAvailable()) {
						if ((row.isAscending() ? numberField.getValue() < 12 : numberField.getValue() > 2) || row.getAmountOfCrossedNumbers() >= 5) {
							list.add(numberField);
						}
					}
				}
			}
		}
		return list;
	}

	public int getTotalPoints() {
		int total = 0;
		for (Color color : Color.values()) {
			total += rows.get(color).getRowScore();
		}
		return total;
	}

	public int getTotalScore() {
		return getTotalPoints() - getTotalPenaltyPoints();
	}

	public int getTotalAmountOfNumbersCrossed() {
		int total = 0;
		for (Color color : Color.values()) {
			total += rows.get(color).getAmountOfCrossedNumbers();
		}
		return total;
	}

	public void save(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO score values (default,session_id,?,?,?,?,?,?,?,?)"
					+ getTotalAmountOfNumbersCrossed()
					+ getTotalPoints()
					+ getTotalPenaltyPoints()
					+ getTotalScore()
					+ getRow(Color.RED).getRowScore()
					+ getRow(Color.YELLOW).getRowScore()
					+ getRow(Color.BLUE).getRowScore()
					+ getRow(Color.GREEN).getRowScore()
			);
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}


	public int getTotalPenaltyPoints() {
		return amountOfPenalties * 5;
	}

}
