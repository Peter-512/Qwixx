package src.view.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import src.model.Color;

import java.util.HashMap;

public class ScoreCardView extends VBox {
	private Label playerName;
	private HashMap<Color, HBox> rowByColorMap;
	private HBox scoreRow;
	private HashMap<Color, Label> scoreByColor;
	private Label penaltyPoints;
	private Label totalScore;
	private HBox penaltyRow;

	public ScoreCardView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		playerName = new Label();
		getChildren().add(playerName);

		// filling rowByColorMap
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
			Button button = new Button("🔒");
			button.setPrefSize(35, 35);
			button.setStyle("-fx-background-color: '%s'".formatted(color));
			row.getChildren().add(button);
			getChildren().add(row);
		}

		//		Setting up penalty boxes
		Label penaltyLabel = new Label("Penalties");
		penaltyRow = new HBox(penaltyLabel);
		for (int i = 0; i < 4; i++) {
			CheckBox penalty = new CheckBox();
			penalty.setDisable(true);
			penaltyRow.getChildren().add(penalty);
		}

		//		Setting up scoreRow
		scoreRow = new HBox();
		scoreByColor = new HashMap<>();
		for (Color color : Color.values()) {
			Label score = new Label();
			scoreByColor.put(color, score);
		}

		getChildren().addAll(penaltyRow, scoreRow);
	}

	private void layoutNodes() {
		setAlignment(Pos.CENTER);
		for (Color color : Color.values()) {
			HBox row = getRowByColor(color);
			row.setSpacing(10);
			row.setAlignment(Pos.CENTER);

			Label score = getScore(color);
			score.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.valueOf(color.name()), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
			score.setPadding(new Insets(10));
			score.setPrefSize(50, 50);
			score.setStyle("-fx-alignment: center");
			scoreRow.getChildren().add(score);
			if (color == Color.BLUE) {
				scoreRow.getChildren().add(new Label("-"));
			} else {
				scoreRow.getChildren().add(new Label("+"));
			}
		}
		penaltyPoints = new Label();
		penaltyPoints.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
		penaltyPoints.setPadding(new Insets(10));
		penaltyPoints.setPrefSize(50, 50);
		penaltyPoints.setStyle("-fx-alignment: center");

		totalScore = new Label();
		totalScore.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
		totalScore.setPadding(new Insets(10));
		totalScore.setPrefSize(100, 50);
		totalScore.setStyle("-fx-alignment: center");

		Label equals = new Label("=");

		scoreRow.getChildren().addAll(penaltyPoints, equals, totalScore);

		penaltyRow.setAlignment(Pos.CENTER);
		penaltyRow.setSpacing(15);

		scoreRow.setAlignment(Pos.CENTER);
		scoreRow.setSpacing(15);
		setSpacing(10);

		setPadding(new Insets(25));
		setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
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

	Label getPlayerName() {
		return playerName;
	}

	HBox getRowByColor(Color color) {
		return rowByColorMap.get(color);
	}

	Button getNumberFieldButton(Color color, int index) {
		return (Button) getRowByColor(color).getChildren().get(index);
	}

	Label getScore(Color color) {
		return scoreByColor.get(color);
	}

	Label getPenaltyPoints() {
		return penaltyPoints;
	}

	Label getTotalScore() {
		return totalScore;
	}

	public HBox getPenaltyRow() {
		return penaltyRow;
	}
}
