package App.src.view.game;

import App.src.model.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class GameView extends BorderPane {
	private VBox vBox;
	private HashMap<Color, HBox> rowByColorMap = new HashMap<>();
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
		vBox = new VBox();

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
			vBox.getChildren().add(row);
		}
		scoreRow = new HBox();
		vBox.getChildren().add(scoreRow);
	}

	private void layoutNodes() {
		setCenter(vBox);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
		for (Color color : Color.values()) {
			getRowByColor(color).setSpacing(10);
			getRowByColor(color).setAlignment(Pos.CENTER);
		}
	}

	HBox getScoreRow() {
		return scoreRow;
	}

	VBox getvBox() {
		return vBox;
	}

	HashMap<Color, HBox> getRowByColorMap() {
		return rowByColorMap;
	}

	HBox getRowByColor(Color color) {
		return rowByColorMap.get(color);
	}

}
