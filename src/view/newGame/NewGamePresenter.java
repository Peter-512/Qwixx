package src.view.newGame;

import src.model.Game;
import src.view.game.GamePresenter;
import src.view.game.GameView;
import src.view.mainMenu.MainMenuPresenter;
import src.view.mainMenu.MainMenuView;

public class NewGamePresenter {
	private final Game model;
	private final NewGameView view;

	public NewGamePresenter(Game model, NewGameView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {
		view.getBackButton().setOnAction(actionEvent -> backToMainMenu());
		view.getStartButton()
		    .setOnAction(actionEvent -> {
			    startGame(view.getNameTextField().getText(), !view.getStartingPlayer().isSelected());
		    });
	}

	private void updateView() {

	}

	private void startGame(String name, boolean startingPlayer) {
		model.startGameSession(name, startingPlayer);
		GameView gameView = new GameView();
		GamePresenter gamePresenter = new GamePresenter(model, gameView);
		view.getScene().setRoot(gameView);
	}

	private void backToMainMenu() {
		MainMenuView mainMenuView = new MainMenuView();
		MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(model, mainMenuView);
		view.getScene().setRoot(mainMenuView);
	}
}
