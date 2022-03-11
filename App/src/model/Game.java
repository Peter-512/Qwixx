package App.src.model;

public class Game {
	public static void main(String[] args) {

		GameSession gameSession = new GameSession(new Player(UI.askForName()), new Player("Skynet"));

		gameSession.startGame();
	}
}
