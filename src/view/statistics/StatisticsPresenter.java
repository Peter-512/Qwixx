package src.view.statistics;

import src.model.Game;
import src.view.mainMenu.MainMenuPresenter;
import src.view.mainMenu.MainMenuView;

public class StatisticsPresenter {
	private Game model;
	private StatisticsView view;


	public StatisticsPresenter(Game model, StatisticsView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {view.getBackButton().setOnAction(actionEvent -> backToMainMenu());}

	private void updateView() {
	}


	private void backToMainMenu() {
		MainMenuView mainMenuView = new MainMenuView();
		MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(model, mainMenuView);
		view.getScene().setRoot(mainMenuView);
	}
}
