package App.src.view.game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

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

	private void initializeNodes() {
		redRow = new HBox();
		for (int i = 2; i < 13; i++) {
			Button button = new Button("%d".formatted(i));
			button.setPrefSize(35, 35);
			button.setStyle("-fx-background-color: red");
			redRow.getChildren().add(button);
		}
		yellowRow = new HBox();
		for (int i = 2; i < 13; i++) {
			Button button = new Button("%d".formatted(i));
			button.setPrefSize(35, 35);
			button.setStyle("-fx-background-color: yellow");

			yellowRow.getChildren().add(button);
		}
		greenRow = new HBox();
		for (int i = 12; i > 1; i--) {
			Button button = new Button("%d".formatted(i));
			button.setPrefSize(35, 35);
			button.setStyle("-fx-background-color: green");
			greenRow.getChildren().add(button);
		}
		blueRow = new HBox();
		for (int i = 12; i > 1; i--) {
			Button button = new Button("%d".formatted(i));
			button.setPrefSize(35, 35);
			button.setStyle("-fx-background-color: blue");
			blueRow.getChildren().add(button);
		}
		scoreRow = new HBox();
		vBox = new VBox(redRow, yellowRow, greenRow, blueRow, scoreRow);
	}

	private void layoutNodes() {
		setCenter(vBox);
		vBox.setAlignment( Pos.CENTER);
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
}
