package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

	public ArrayList<Color> getLockedRowColors() {
		ArrayList<Color> lockedRowColors = new ArrayList<>();
		for (Color color : Color.values()) {
			if (rows.get(color).isLocked()) {
				lockedRowColors.add(color);
			}
		}
		return lockedRowColors;
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
			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO score (session_id, total_amount_of_numbers_crossed, total_points, penalty_points, total_score, red_points, yellow_points, blue_points, green_points) 
					VALUES (CURRVAL('player_session_session_id_seq'),?,?,?,?,?,?,?,?)
					""");
			statement.setInt(1, getTotalAmountOfNumbersCrossed());
			statement.setInt(2, getTotalPoints());
			statement.setInt(3, getTotalPenaltyPoints());
			statement.setInt(4, getTotalScore());
			statement.setInt(5, getRow(Color.RED).getRowScore());
			statement.setInt(6, getRow(Color.YELLOW).getRowScore());
			statement.setInt(7, getRow(Color.GREEN).getRowScore());
			statement.setInt(8, getRow(Color.BLUE).getRowScore());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public int getTotalPenaltyPoints() {
		return amountOfPenalties * 5;
	}

}
