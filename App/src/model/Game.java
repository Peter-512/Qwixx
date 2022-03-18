package App.src.model;

public class Game {
	GameSession gameSession;

	public Game() {
		GameSession gameSession = new GameSession(new Player(UI.askForName()), new Player("Skynet"));
		gameSession.startGame();
	}

}
