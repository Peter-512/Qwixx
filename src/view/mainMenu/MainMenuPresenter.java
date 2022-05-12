package src.view.mainMenu;

import src.model.Game;
import src.view.newGame.NewGamePresenter;
import src.view.newGame.NewGameView;
import src.view.rules.RulesPresenter;
import src.view.rules.RulesView;
import src.view.statistics.StatisticsPresenter;
import src.view.statistics.StatisticsView;

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
		view.getRulesButton().setOnAction(e -> setRulesView());
		view.getNewGameButton().setOnAction(e -> setNewGameView());
		view.getStatisticsButton().setOnAction(e -> setStatisticsView());
	}

	private void setNewGameView() {
		NewGameView newGameView = new NewGameView();
		NewGamePresenter newGamePresenter = new NewGamePresenter(model, newGameView);
		view.getScene().setRoot(newGameView);

	}

	private void setRulesView() {
		RulesView rulesView = new RulesView();
		RulesPresenter rulesPresenter = new RulesPresenter(model, rulesView);
		view.getScene().setRoot(rulesView);
	}

	private void setStatisticsView() {
		StatisticsView statisticsView = new StatisticsView();
		StatisticsPresenter statisticsPresenter = new StatisticsPresenter(model, statisticsView);
		view.getScene().setRoot(statisticsView);
	}

	private void updateView() {

	}


}
