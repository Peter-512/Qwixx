package App.src.view.rules;

import App.src.model.Game;
import App.src.view.mainMenu.MainMenuPresenter;
import App.src.view.mainMenu.MainMenuView;

public class RulesPresenter {
	private Game model;
	private RulesView view;

	public RulesPresenter(Game model, RulesView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {view.getBackButton().setOnAction(actionEvent -> backToMainMenu());


	}

	private void updateView() {

	}

	private void backToMainMenu(){
		MainMenuView mainMenuView = new MainMenuView();
		MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(model,mainMenuView);
		view.getScene().setRoot(mainMenuView);
	}

}
