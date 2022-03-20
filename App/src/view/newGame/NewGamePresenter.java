package App.src.view.newGame;

import App.src.model.Game;
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
		view.getBack().setOnAction(actionEvent -> backToMainMenu());
	}

	private void updateView() {

	}

	private void backToMainMenu() {
		MainMenuView mainMenuView = new MainMenuView();
		MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(model, mainMenuView);
		view.getScene().setRoot(mainMenuView);
	}
}
