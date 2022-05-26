package src.view.endScreen;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import src.model.Game;
import src.model.PlayerSession;
import src.model.Turn;
import src.view.mainMenu.MainMenuPresenter;
import src.view.mainMenu.MainMenuView;

public class EndScreenPresenter {
	private final Game model;
	private final EndScreenView view;

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
		for (PlayerSession playerSession : model.getGameSession().getPlayerSessions()) {
			XYChart.Series<Number, Number> series = new XYChart.Series<>();

			series.getData().add(new XYChart.Data<>(0, 0));
			int cumPoints = 0;
			for (Turn turn : playerSession.getTurns()) {
				XYChart.Data<Number, Number> data = new XYChart.Data<>(turn.getTurnNumber(), cumPoints);
				cumPoints += turn.getTotalPoints();
				series.getData().add(data);
			}
			series.setName(playerSession.getPlayerName());
			view.getChart().getData().add(series);

			for (XYChart.Series<Number, Number> s : view.getChart().getData()) {
				for (XYChart.Data<Number, Number> d : s.getData()) {
					Tooltip.install(d.getNode(), new Tooltip(d.getYValue().toString()));
				}
			}
		}
	}


}
