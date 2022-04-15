package App.src.view.game;

import App.src.model.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ScoreCardPresenter {
	ScoreCard model;
	ScoreCardView view;
	GameSession gameSession;

	public ScoreCardPresenter(ScoreCard model, ScoreCardView view, GameSession gameSession) {
		this.model = model;
		this.view = view;
		this.gameSession = gameSession;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {
		for (Color color : Color.values()) {
			HBox rowView = view.getRowByColor(color);
			for (int i = 0; i < rowView.getChildren().size(); i++) {
				Button button = (Button) rowView.getChildren().get(i);
				int finalI = i;
				button.setOnAction(actionEvent -> {
					Row row = model.getRow(color);
					if (!row.getNumberField(finalI).isDisabled()) {
						row.getNumberField(finalI).setCrossed();
						row.disableNumberFieldsUntil(finalI);
						//						TODO pass correct numbers to takeAction
						model.getPlayerSession().getCurrentTurn().takeAction(0, 0, 0);
						updateView();
					}
				});
			}
		}
	}

	void updateView() {
		disableAllNumberFields();
		//		Enabling numberFields based on dice rolls
		//		FIXME find a different way to deal with no turns being active yet
		//		    null check on getCurrentTurn()?
		try {
			if (model.getPlayerSession().getCurrentTurn().getNumberOfActions() == 0) {
				HashMap<Color, NumberField> map = model.getPublicNumberFields(gameSession.totalPublicThrow());
				map.forEach((color, numberField) -> {
					view.getNumberFieldButton(color, numberField.getIndex()).setDisable(false);
				});
			}
		} catch (NoSuchElementException e) {
			System.out.println("No turn yet");
		}

		//		FIXME find a different way to deal with no turns being active yet
		//		    null check on getCurrentTurn()?
		try {
			if (gameSession.getActivePlayerSession()
			               .getScoreCard()
			               .equals(model) && gameSession.getActivePlayerSession()
			                                            .getCurrentTurn()
			                                            .getNumberOfActions() == 1) {
				enableColoredNumberFields();
			}
		} catch (NoSuchElementException e) {
			System.out.println("No turn yet");
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
		view.getTotalScore().setText(String.valueOf(model.getTotalPoints()));

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
		HashMap<Color, ArrayList<NumberField>> map = model.getColoredNumberFields(gameSession.getColoredDicePool(), gameSession.getPublicDicePool());
		map.forEach((color, numberFields) -> {
			for (NumberField numberField : numberFields) {
				view.getNumberFieldButton(color, numberField.getIndex()).setDisable(false);
			}
		});
	}
}
