package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

	public void save(Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement("""
					INSERT INTO player (name) VALUES (?)
					""");
			statement.setString(1, getName());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
