package App.src.view.game;

import App.src.model.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class ScoreCardView extends VBox {
	private Label playerName;
	private HashMap<Color, HBox> rowByColorMap;
	private HBox scoreRow;

	public ScoreCardView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		playerName = new Label();
		getChildren().add(playerName);

		rowByColorMap = new HashMap<>();
		for (Color color : Color.values()) {
			HBox row = new HBox();
			rowByColorMap.put(color, row);
			if (color.ordinal() < 2) {
				for (int i = 2; i < 13; i++) {
					row.getChildren().add(createButton(i, color, false));
				}
			} else {
				for (int i = 12; i > 1; i--) {
					row.getChildren().add(createButton(i, color, true));
				}
			}
			getChildren().add(row);
		}
		scoreRow = new HBox();
		getChildren().add(scoreRow);
	}

	private void layoutNodes() {
		setAlignment(Pos.CENTER);
		for (Color color : Color.values()) {
			getRowByColor(color).setSpacing(10);
			getRowByColor(color).setAlignment(Pos.CENTER);
		}
		setSpacing(10);
	}

	private Button createButton(int label, Color color, boolean whiteText) {
		Button button = new Button("%d".formatted(label));
		button.setPrefSize(35, 35);
		button.setStyle("-fx-background-color: '%s'".formatted(color));
		if (whiteText) {
			button.setStyle("-fx-background-color: '%s'; -fx-text-fill: white".formatted(color));
		}
		button.setDisable(true);
		return button;
	}

	Label getPlayerName() {
		return playerName;
	}

	HashMap<Color, HBox> getRowByColorMap() {
		return rowByColorMap;
	}

	HBox getRowByColor(Color color) {
		return rowByColorMap.get(color);
	}

	HBox getScoreRow() {
		return scoreRow;
	}
}
