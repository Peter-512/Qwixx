package App.src;

public class Game {
	public static void main(String[] args) {
		UI ui = new UI();

		GameSession gameSession = new GameSession(new Player(ui.askForName()), new Player("Skynet"));
	}
}
