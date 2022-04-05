package App.src.view.game;

import App.src.model.Color;
import App.src.model.Game;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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
					updateView();
				});
			}
		}
	}

	private void updateView() {
		for (Color color : Color.values()) {
			model.getGameSession()
			     .getPlayerSession()
			     .getScoreCard()
			     .getRow(color).getNumberFields().forEach((integer, numberField) -> {
				     if (numberField.isCrossed()) {
					     Button button = (Button) view.getRowByColor(color).getChildren().get(integer);
					     button.setText("X");
				     }
			     });
		}
	}
}
