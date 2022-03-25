package App.src.view.game;

import App.src.model.Color;
import App.src.model.Game;
import javafx.scene.control.Button;

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
		for (int i = 0; i < view.getRedRow().getChildren().size(); i++) {
			Button button = (Button) view.getRedRow().getChildren().get(i);
			int finalI = i;
			button.setOnAction(actionEvent -> {
				model.getGameSession()
				     .getPlayerSession()
				     .getScoreCard()
				     .getRow(Color.RED)
				     .getNumberField(finalI)
				     .setCrossed();
				updateView();
			});
		}
	}

	private void updateView() {
		model.getGameSession()
		     .getPlayerSession()
		     .getScoreCard()
		     .getRow(Color.RED)
		     .getNumberFields()
		     .forEach((integer, numberField) -> {
			     if (numberField.isCrossed()) {
				     final Button button = (Button) view.getRedRow().getChildren().get(integer);
				     button.setText("X");
			     }
		     });
	}


}
