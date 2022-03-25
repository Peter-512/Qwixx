package App.src.view.game;

import App.src.model.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView extends BorderPane {
	private VBox vBox;
	private HBox redRow;
	private HBox yellowRow;
	private HBox greenRow;
	private HBox blueRow;
	private HBox scoreRow;


	public GameView() {
		initializeNodes();
		layoutNodes();
	}

	private Button createButton(int label, Color color, boolean whiteText) {
		Button button = new Button("%d".formatted(label));
		button.setPrefSize(35, 35);
		button.setStyle("-fx-background-color: '%s'".formatted(color));
		if (whiteText) {
			button.setStyle("-fx-background-color: '%s'; -fx-text-fill: white".formatted(color));
		}
		return button;
	}

	private void initializeNodes() {
		redRow = new HBox();
		for (int i = 2; i < 13; i++) {
			redRow.getChildren().add(createButton(i, Color.RED, false));
		}
		yellowRow = new HBox();
		for (int i = 2; i < 13; i++) {
			yellowRow.getChildren().add(createButton(i, Color.YELLOW, false));
		}
		greenRow = new HBox();
		for (int i = 12; i > 1; i--) {
			greenRow.getChildren().add(createButton(i, Color.GREEN, true));
		}
		blueRow = new HBox();
		for (int i = 12; i > 1; i--) {
			blueRow.getChildren().add(createButton(i, Color.BLUE, true));
		}
		scoreRow = new HBox();
		vBox = new VBox(redRow, yellowRow, greenRow, blueRow, scoreRow);
	}

	private void layoutNodes() {
		setCenter(vBox);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
		redRow.setAlignment(Pos.CENTER);
		redRow.setSpacing(10);
		yellowRow.setAlignment(Pos.CENTER);
		yellowRow.setSpacing(10);
		greenRow.setAlignment(Pos.CENTER);
		greenRow.setSpacing(10);
		blueRow.setAlignment(Pos.CENTER);
		blueRow.setSpacing(10);
	}

	HBox getRedRow() {
		return redRow;
	}

	HBox getYellowRow() {
		return yellowRow;
	}

	HBox getGreenRow() {
		return greenRow;
	}

	HBox getBlueRow() {
		return blueRow;
	}

	HBox getScoreRow() {
		return scoreRow;
	}

	VBox getvBox() {
		return vBox;
	}
}
