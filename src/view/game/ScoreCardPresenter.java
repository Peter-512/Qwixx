package src.view.game;

import src.model.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.*;

public class ScoreCardPresenter {
	private final ScoreCard model;
	private final ScoreCardView view;
	private final GameSession gameSession;
	private final HashMap<Button, NumberField> buttonByNumberFieldHashMap;
	private final HashMap<NumberField, Button> numberFieldByButtonHashMap;

	public ScoreCardPresenter(ScoreCard model, ScoreCardView view, GameSession gameSession) {
		this.model = model;
		this.view = view;
		this.gameSession = gameSession;

		numberFieldByButtonHashMap = new HashMap<>();
		buttonByNumberFieldHashMap = new HashMap<>();
		fillHashMaps();

		addEventHandlers();
		updateView();
	}

	private void fillHashMaps() {
		for (Color color : Color.values()) {
			for (int i = 0; i < view.getRowByColor(color).getChildren().size() - 1; i++) {
				Button button = (Button) view.getRowByColor(color).getChildren().get(i);
				NumberField numberField = model.getRow(color).get(i);
				buttonByNumberFieldHashMap.put(button, numberField);
				numberFieldByButtonHashMap.put(numberField, button);
			}
		}
	}

	private void addEventHandlers() {
		for (Map.Entry<Button, NumberField> buttonNumberFieldEntry : buttonByNumberFieldHashMap.entrySet()) {
			Button button = buttonNumberFieldEntry.getKey();
			NumberField numberField = buttonNumberFieldEntry.getValue();
			button.setOnAction(actionEvent -> {
				if (!numberField.isDisabled()) {
					numberField.setCrossed();
					numberField.getRow().disableNumberFieldsBefore(numberField.getIndex());
					//					TODO pass correct numbers to takeAction
					model.getPlayerSession().getCurrentTurn().takeAction(0, 0, 0);
					updateView();
				}
			});
		}
	}

	void updateView() {
		disableAllNumberFields();
		//		Enabling numberFields based on dice rolls
		if (model.getPlayerSession().getCurrentTurn() != null) {
			if (model.getPlayerSession().getCurrentTurn().getNumberOfActions() == 0) {
				ArrayList<NumberField> list = model.getPublicNumberFields(gameSession.totalPublicThrow());
				for (NumberField numberField : list) {
					getButtonByNumberField(numberField).setDisable(false);
				}
			}
			if (gameSession.getActivePlayerSession()
			               .getScoreCard()
			               .equals(model) && gameSession.getActivePlayerSession()
			                                            .getCurrentTurn()
			                                            .getNumberOfActions() == 1) {
				enableColoredNumberFields();
			}
		}

		//		Updating crossed out and disabled NumberFields
		for (Color color : Color.values()) {
			ArrayList<NumberField> numberFields = model.getRow(color).getNumberFields();
			for (NumberField numberField : numberFields) {
				if (numberField.isCrossed()) {
					Button button = view.getNumberFieldButton(color, numberField.getIndex());
					button.setText("✓");
				} else if (numberField.isDisabled()) {
					Button button = view.getNumberFieldButton(color, numberField.getIndex());
					button.setText("X");
				}
			}
			//			Updating row scores
			view.getScore(color).setText(String.valueOf(model.getRow(color).getRowScore()));
		}
		//		Updating penalty points
		view.getPenaltyPoints().setText(String.valueOf(model.getTotalPenaltyPoints()));
		//		Updating total score
		view.getTotalScore().setText(String.valueOf(model.getTotalScore()));

		if (model.getTotalPenaltyPoints() > 0) {
			CheckBox cb = (CheckBox) view.getPenaltyRow().getChildren().get(model.getAmountOfPenalties());
			cb.setSelected(true);
		}
	}

	private void disableAllNumberFields() {
		if (gameSession.isHumanSession(model.getPlayerSession())) {
			for (Color color : Color.values()) {
				for (Node n : view.getRowByColor(color).getChildren()) {
					Button button = (Button) n;
					button.setDisable(true);
				}
			}
		} else {
			for (Color color : Color.values()) {
				for (Node n : view.getRowByColor(color).getChildren()) {
					Button button = (Button) n;
					button.setMouseTransparent(true);
				}
			}
		}
	}

	private void enableColoredNumberFields() {
		ArrayList<NumberField> list = model.getColoredNumberFields(gameSession.getColoredDicePool(), gameSession.getPublicDicePool());
		for (NumberField numberField : list) {
			getButtonByNumberField(numberField).setDisable(false);
		}
	}

	public NumberField getNumberFieldByButton(Button button) {
		return buttonByNumberFieldHashMap.get(button);
	}

	public Button getButtonByNumberField(NumberField numberField) {
		return numberFieldByButtonHashMap.get(numberField);
	}
}
