package src.view.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;
import src.model.ColoredDie;
import src.model.Game;
import src.model.PlayerSession;
import src.model.Turn;
import src.view.endScreen.EndScreenPresenter;
import src.view.endScreen.EndScreenView;

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
			scoreCardPresenters[i] = new ScoreCardPresenter(
					model.getGameSession().getPlayerSessions()[i].getScoreCard(),
					(ScoreCardView) view.getScoreCards().getChildren().get(i),
					this,
					model.getGameSession());
		}

		addEventHandlers();
		initialViewUpdate();
		updateView();
	}

	private void addEventHandlers() {

		//		Hitting roll dice button
		view.getRollDiceButton().setOnAction(actionEvent -> {
			view.getRollDiceButton().setDisable(true);
			model.getGameSession().changeActivePlayer();
			for (PlayerSession playerSession : model.getGameSession().getPlayerSessions()) {
				playerSession.newTurn();
			}
			model.getGameSession().throwAllDice();
			model.getGameSession()
			     .getBotSession()
			     .takeTurn(model.getGameSession().totalPublicThrow(), model.getGameSession()
			                                                               .getColoredDicePool(), model.getGameSession()
			                                                                                           .getPublicDicePool());
			updateView();
		});

		//		Hitting the penalty button
		view.getPenaltyButton().setOnAction(actionEvent -> {
			model.getGameSession().getHumanSession().takePenaltyAction();
			view.getPenaltyButton().setDisable(true);
			updateView();
		});

		//		Hitting the pass button
		view.getPassButton().setOnAction(actionEvent -> {
			view.getPassButton().setDisable(true);
			model.getGameSession().getHumanSession().passAction();
			updateView();
		});
	}

	void updateView() {
		//		set current Player Text
		view.getCurrentPlayer()
		    .setText(String.format("%s's turn", model.getGameSession().getActivePlayerSession().getPlayerName()));

		updateDicePools();
		updateScoreCards();

		setButtonsRight();

		if (model.getGameSession().gameOver()) {
			final int botScore = model.getGameSession().getPlayerSessions()[0].getScoreCard().getTotalScore();
			final int playerScore = model.getGameSession().getPlayerSessions()[1].getScoreCard().getTotalScore();
			boolean playerWon = playerScore > botScore;
			EndScreenView endScreenView = new EndScreenView(playerWon);
			EndScreenPresenter endScreenPresenter = new EndScreenPresenter(model, endScreenView);
			view.getScene().setRoot(endScreenView);
		}
	}

	private void setButtonsRight() {
		final PlayerSession humanSession = model.getGameSession().getHumanSession();
		final Button rollDiceButton = view.getRollDiceButton();
		final Button penaltyButton = view.getPenaltyButton();
		final Button passButton = view.getPassButton();
		final Turn turn = humanSession.getCurrentTurn();
		final int numberOfActions = turn != null ? turn.getNumberOfActions() : -1; // -1 for start of game (no turn has started yet)

		rollDiceButton.setDisable(true);
		penaltyButton.setDisable(true);
		passButton.setDisable(true);

		if (humanSession.isActivePlayer()) {
			switch (numberOfActions) {
				case 0 -> passButton.setDisable(false);
				case 1 -> {
					if (turn.getLastAction().isPassedTurn()) penaltyButton.setDisable(false);
					else passButton.setDisable(false);
				}
				case 2, -1 -> rollDiceButton.setDisable(false);
			}
		} else {
			switch (numberOfActions) {
				case 0 -> passButton.setDisable(false);
				case 1, -1 -> rollDiceButton.setDisable(false);
			}
		}
	}

	private void updateScoreCards() {
		for (ScoreCardPresenter scoreCardPresenter : scoreCardPresenters) {
			scoreCardPresenter.updateView();
		}
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
		final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
			final long diff = System.currentTimeMillis() - model.getGameSession().getStartTime();
			view.getTimeLabel().setText(timeFormat.format(diff));
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}
