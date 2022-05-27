package src.view.endScreen;

import javafx.scene.chart.NumberAxis;
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
		PlayerSession humanSession = model.getGameSession().getHumanSession();

		for (PlayerSession playerSession : model.getGameSession().getPlayerSessions()) {
			XYChart.Series<Number, Number> pointSeries = new XYChart.Series<>();

			pointSeries.getData().add(new XYChart.Data<>(0, 0));
			int cumPoints = 0;
			for (Turn turn : playerSession.getTurns()) {
				cumPoints += turn.getTotalPoints();
				pointSeries.getData().add(new XYChart.Data<>(turn.getTurnNumber(), cumPoints));
			}
			pointSeries.setName(playerSession.getPlayerName());
			view.getPointsChart().getData().add(pointSeries);

			for (XYChart.Series<Number, Number> s : view.getPointsChart().getData()) {
				for (XYChart.Data<Number, Number> d : s.getData()) {
					Tooltip.install(d.getNode(), new Tooltip(String.format("""
							Turn: %d
							Points: %d""", ((int) d.getXValue()), ((int) d.getYValue()))));
				}
			}
		}

		XYChart.Series<Number, Number> durationSeries = new XYChart.Series<>();
		for (Turn turn : humanSession.getTurns()) {
			durationSeries.getData().add(new XYChart.Data<>(turn.getTurnNumber(), turn.getTurnDurationInSeconds()));
		}
		durationSeries.setName(humanSession.getPlayerName());
		view.getDurationsChart().getData().add(durationSeries);
		((NumberAxis) view.getDurationsChart().getXAxis()).setUpperBound(humanSession.getCurrentTurn()
		                                                                             .getTurnNumber() + 1);
		for (XYChart.Series<Number, Number> s : view.getDurationsChart().getData()) {
			for (XYChart.Data<Number, Number> d : s.getData()) {
				Tooltip.install(d.getNode(), new Tooltip(String.format("""
						Turn: %d
						Duration: %.2f seconds""", ((int) d.getXValue()), ((float) d.getYValue()))));
			}
		}

		final int botScore = model.getGameSession().getBotSession().getScoreCard().getTotalScore();
		final int playerScore = humanSession.getScoreCard().getTotalScore();
		boolean playerWon = playerScore > botScore;
		view.getWhoWonText().setText(playerWon ? "You won!" : "You lost!");

		view.getAvgDuration().setText(String.format("%.2f milliseconds", humanSession.getAverageDurationPerTurn()));
		view.getAvgPoints().setText(String.format("%.2f points", humanSession.getAveragePointsPerTurn()));
		view.getAvgNumbersMissed()
		    .setText(String.format("%.2f numbersfields", humanSession.getAverageNumbersMissedPerTurn()));

		String endState = switch (model.getGameSession().getGameState()) {
			case ROWS -> "Two or more rows were locked";
			case PENALTIES -> "Maximum amount of penalties were reached";
			case RUNNING -> null;
		};
		view.getEndState().setText(endState);
	}


}
