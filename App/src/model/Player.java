package App.src.model;

public class Player {
	private String name;
	private PlayerSession playerSession;

	public Player(String name) {
		this.name = name;
		playerSession = new PlayerSession();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
