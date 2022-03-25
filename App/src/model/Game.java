package App.src.model;

public class Game {
	GameSession gameSession;

	public GameSession getGameSession() {
		return gameSession;
	}

	public void startGameSession(String name) {
		gameSession = new GameSession(name);
	}
}
