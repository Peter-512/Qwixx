package App.src.view.game;

import App.src.model.ColoredDie;
import App.src.model.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GamePresenter {
	Game model;
	GameView view;
	ScoreCardPresenter[] scoreCardPresenters;

	public GamePresenter(Game model, GameView view) {
		this.model = model;
		this.view = view;

		scoreCardPresenters = new ScoreCardPresenter[2];
		for (int i = 0; i < 2; i++) {
			scoreCardPresenters[i] = new ScoreCardPresenter(model.getGameSession()
			                                                     .getPlayerSessions()[i].getScoreCard(),
					(ScoreCardView) view.getScoreCards().getChildren().get(i),
					model.getGameSession());
		}

		addEventHandlers();
		initialViewUpdate();
	}

	private void addEventHandlers() {

		//		Hitting roll dice button
		view.getRollDiceButton().setOnAction(actionEvent -> {
			model.getGameSession().throwAllDice();
			updateDicePools();
			for (ScoreCardPresenter scoreCardPresenter : scoreCardPresenters) {
				scoreCardPresenter.updateView();
			}
		});
	}

	private void updateDicePools() {
		//		Updating colored dice
		model.getGameSession().getColoredDicePool().getDice().forEach(d -> {
			ColoredDie die = (ColoredDie) d;
			view.getDieByColor(die.getColor()).setText(String.valueOf(die.getValue()));
		});

		//		Updating public dice
		for (int i = 0; i < model.getGameSession().getPublicDicePool().getDice().size(); i++) {
			view.getPublicDice()[i].setText(String.valueOf(model.getGameSession()
			                                                    .getPublicDicePool()
			                                                    .getDice()
			                                                    .get(i)
			                                                    .getValue()));
		}
	}

	private void initialViewUpdate() {

		//		Displaying the players name on the scoreCard
		for (int i = 0; i < 2; i++) {
			((ScoreCardView) view.getScoreCards().getChildren().get(i)).getPlayerName()
			                                                           .setText(String.format("%s's Score Card", model.getGameSession()
			                                                                                                          .getPlayerSessions()[i].getPlayerName()));
		}

		//		Displaying the time
		DateFormat timeFormat = new SimpleDateFormat("mm:ss");
		final Timeline timeline = new Timeline(
				new KeyFrame(
						Duration.millis(1000),
						event -> {
							final long diff = System.currentTimeMillis() - model.getGameSession().getStartTime();
							view.getTimeLabel().setText(timeFormat.format(diff));
						}
				)
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}
