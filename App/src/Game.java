package App.src;

import App.src.model.GameSession;
import App.src.model.Player;
import App.src.model.UI;

public class Game {
	public static void main(String[] args) {

		GameSession gameSession = new GameSession(new Player(UI.askForName()), new Player("Skynet"));

		gameSession.startGame();
	}
}
