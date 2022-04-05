package App.src.view.newGame;

import App.src.model.Game;
import App.src.view.game.GamePresenter;
import App.src.view.game.GameView;
import App.src.view.mainMenu.MainMenuPresenter;
import App.src.view.mainMenu.MainMenuView;

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
		view.getStartButton().setOnAction(actionEvent -> startGame(view.getNameTextField().getText()));
	}

	private void updateView() {

	}

	private void startGame(String name) {
		model.startGameSession(name);
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
