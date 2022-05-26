package src.model;

import java.sql.*;

public class Player {
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int save(Connection connection) {
		try {
			PreparedStatement nameExists = connection.prepareStatement("SELECT player_id FROM player WHERE name = ?");
			nameExists.setString(1, name);

			ResultSet rs = nameExists.executeQuery();
			if (rs.next()) {
				return rs.getInt("player_id");
			}

			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO player (name) VALUES (?)
					""");
			statement.setString(1, getName());
			statement.executeUpdate();

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return 0;
	}
}
