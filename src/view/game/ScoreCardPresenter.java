package src.view.game;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import src.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreCardPresenter {
	private final ScoreCard model;
	private final ScoreCardView view;
	private final GamePresenter parentPresenter;
	private final GameSession gameSession;
	private final HashMap<NumberField, Button> numberFieldByButtonHashMap;

	public ScoreCardPresenter(ScoreCard model, ScoreCardView view, GamePresenter parentPresenter, GameSession gameSession) {
		this.model = model;
		this.view = view;
		this.parentPresenter = parentPresenter;
		this.gameSession = gameSession;

		numberFieldByButtonHashMap = new HashMap<>();
		fillHashMap();

		addEventHandlers();
		updateView();
	}

	private void fillHashMap() {
		for (Color color : Color.values()) {
			for (int i = 0; i < view.getRowByColor(color).getChildren().size() - 1; i++) {
				Button button = (Button) view.getRowByColor(color).getChildren().get(i);
				NumberField numberField = model.getRow(color).get(i);
				numberFieldByButtonHashMap.put(numberField, button);
			}
		}
	}

	private void addEventHandlers() {
		for (Map.Entry<NumberField, Button> numberFieldButtonEntry : numberFieldByButtonHashMap.entrySet()) {
			Button button = numberFieldButtonEntry.getValue();
			NumberField numberField = numberFieldButtonEntry.getKey();
			button.setOnAction(actionEvent -> {
				if (!numberField.isDisabled()) {
					int rowScoreBefore = numberField.getRow().getRowScore();
					int numbersMissed = numberField.getRow().getAmountOfNumberFieldsBefore(numberField.getIndex());
					numberField.setCrossed();
					int rowScoreAfter = numberField.getRow().getRowScore();
					numberField.getRow().disableNumberFieldsBefore(numberField.getIndex());
					if (numberField == numberField.getRow().getLast()) {
						numberField.getRow().setLocked();
						final Color color = numberField.getRow().getColor();
						final Label dieLabel = parentPresenter.view.getDieByColor(color);
						final Die die = gameSession.getColoredDicePool().get(color);
						if (die != null) {
							gameSession.getColoredDicePool().remove(die);
						}
						parentPresenter.view.getDicePools().getChildren().remove(dieLabel);
						parentPresenter.view.getDieByColorMap().remove(color);
					}

					model.getPlayerSession().takeAction(numbersMissed, rowScoreAfter - rowScoreBefore);
					updateView();
					parentPresenter.updateView();
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
		for (Color color : Color.values()) {
			for (Node n : view.getRowByColor(color).getChildren()) {
				Button button = (Button) n;
				if (gameSession.isHumanSession(model.getPlayerSession())) {
					button.setDisable(true);
				} else {
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

	public Button getButtonByNumberField(NumberField numberField) {
		return numberFieldByButtonHashMap.get(numberField);
	}
}
