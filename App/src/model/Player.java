package App.src.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public void storePlayer(int player_id, String name) {
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/qwixx1",
					"postgres",
					"Student_1234");
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO player values (default,?)" + getName());
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
