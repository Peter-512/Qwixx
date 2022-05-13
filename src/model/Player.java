package src.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO player values (default,?)" + getName());

			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
