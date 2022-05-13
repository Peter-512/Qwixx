package src.view.endScreen;

import src.Main;
import src.model.Game;
import src.view.mainMenu.MainMenuPresenter;
import src.view.mainMenu.MainMenuView;

public class EndScreenPresenter {
	private Game model;
	private EndScreenView view;

	public EndScreenPresenter(Game model, EndScreenView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {
		view.getButton().setOnAction(actionEvent -> {
			MainMenuView newGame = new MainMenuView();
			Game model = new Game();
			new MainMenuPresenter(model, newGame);
			view.getScene().setRoot(newGame);
		});
	}

	private void updateView() {

	}


}
