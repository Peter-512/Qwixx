package App.src.view.game;

import App.src.model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePresenter {
	Game model;
	GameView view;

	public GamePresenter(Game model, GameView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {

		//		Clicking a numberField on the scoreCard
		for (Color color : Color.values()) {
			HBox rowView = view.getRowByColor(color);
			for (int i = 0; i < rowView.getChildren().size(); i++) {
				Button button = (Button) rowView.getChildren().get(i);
				int finalI = i;
				button.setOnAction(actionEvent -> {
					Row row = model.getGameSession()
					               .getPlayerSession()
					               .getScoreCard()
					               .getRow(color);
					if (!row.getNumberField(finalI).isDisabled()) {
						row.getNumberField(finalI).setCrossed();
						row.disableNumberField(finalI);
						updateView();
						disableAllNumberFields();
						enableColoredNumberFields();
					}
				});
			}
		}

		//		Hitting roll dice button
		view.getRollDiceButton().setOnAction(actionEvent -> {
			model.getGameSession().throwAllDice();
			updateView();
		});
	}

	private void enableColoredNumberFields() {
		HashMap<Color, ArrayList<NumberField>> map = model.getGameSession().getColoredNumberFields();
		map.forEach((color, numberFields) -> {
			for (NumberField numberField : numberFields) {
				view.getRowByColor(color).getChildren().get(numberField.getIndex()).setDisable(false);
			}
		});
	}

	private void updateView() {
		//		Enabling numberFields based on dice rolls
		HashMap<Color, NumberField> map = model.getGameSession().getPublicNumberFields();
		map.forEach((color, numberField) -> {
			view.getRowByColor(color).getChildren().get(numberField.getIndex()).setDisable(false);
		});

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

		//		Updating crossed out and disabled NumberFields
		for (Color color : Color.values()) {
			ArrayList<NumberField> numberFields = model.getGameSession()
			                                           .getPlayerSession()
			                                           .getScoreCard()
			                                           .getRow(color).getNumberFields();
			for (NumberField numberField : numberFields) {
				if (numberField.isCrossed()) {
					Button button = (Button) view.getRowByColor(color)
					                             .getChildren()
					                             .get(numberFields.indexOf(numberField));
					button.setText("✓");
				} else if (numberField.isDisabled()) {
					Button button = (Button) view.getRowByColor(color)
					                             .getChildren()
					                             .get(numberFields.indexOf(numberField));
					button.setText("X");
				}
			}
		}

		//		Displaying the players name on the scoreCard
		view.getPlayerName()
		    .setText(String.format("%s's Score Card", model.getGameSession().getPlayerSession().getPlayerName()));

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

	private void disableAllNumberFields() {
		for (Color color : Color.values()) {
			for (Node n : view.getRowByColor(color).getChildren()) {
				Button button = (Button) n;
				button.setDisable(true);
			}
		}
	}

}
