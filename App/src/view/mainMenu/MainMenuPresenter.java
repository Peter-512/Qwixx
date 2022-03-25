package App.src.view.mainMenu;

import App.src.model.Game;
import App.src.view.newGame.NewGamePresenter;
import App.src.view.newGame.NewGameView;

public class MainMenuPresenter {
	private Game model;
	private MainMenuView view;


	public MainMenuPresenter(Game model, MainMenuView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {
		view.getNewGame().setOnAction(e -> setGameView());
	}

	private void setGameView() {
		NewGameView newGameView = new NewGameView();
		NewGamePresenter newGamePresenter = new NewGamePresenter(model, newGameView);
		view.getScene().setRoot(newGameView);
	}

	private void updateView() {

	}


}
