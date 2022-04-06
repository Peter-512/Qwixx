package App.src.view.game;

import App.src.model.Color;
import App.src.model.ColoredDie;
import App.src.model.Game;
import App.src.model.NumberField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

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
		for (Color color : Color.values()) {
			HBox row = view.getRowByColor(color);
			for (int i = 0; i < row.getChildren().size(); i++) {
				Button button = (Button) row.getChildren().get(i);
				int finalI = i;
				button.setOnAction(actionEvent -> {
					model.getGameSession()
					     .getPlayerSession()
					     .getScoreCard()
					     .getRow(color).getNumberField(finalI).setCrossed();
					model.getGameSession().getPlayerSession().getScoreCard().getRow(color).disableNumberFields(finalI);
					updateView();
				});
			}
		}
		view.getRollDiceButton().setOnAction(actionEvent -> {
			model.getGameSession().throwAllDice();
			updateView();
		});
	}

	private void updateView() {
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

		//		Updating crossed out NumberFields
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
	}
}
