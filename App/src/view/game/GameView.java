package App.src.view.game;

import App.src.model.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.HashMap;

public class GameView extends BorderPane {
	private VBox scoreCards;
	private HashMap<Color, HBox> rowByColorMap = new HashMap<>();
	private HBox scoreRow;
	private VBox dicePools;
	private HashMap<Color, Label> dieByColorMap = new HashMap<>();
	private Label[] publicDice = new Label[2];
	private Button rollDiceButton;


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

		//		Setting up scoreCard
		scoreCards = new VBox();

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
			scoreCards.getChildren().add(row);
		}
		scoreRow = new HBox();
		scoreCards.getChildren().add(scoreRow);

		//		Setting up dice
		dicePools = new VBox();
		for (Color color : Color.values()) {
			Label die = new Label("1");
			dieByColorMap.put(color, die);
			dicePools.getChildren().add(die);
		}
		for (int i = 0; i < publicDice.length; i++) {
			publicDice[i] = new Label("1");
			dicePools.getChildren().add(publicDice[i]);
		}
		rollDiceButton = new Button("Roll dice");
		dicePools.getChildren().add(rollDiceButton);
	}

	private void layoutNodes() {

//		rollDiceButton.setPrefSize(25, 25);
		for (Label die : publicDice) {
			die.setPrefSize(50, 50);
			die.setPadding(new Insets(15));
			die.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
		}
		dieByColorMap.forEach((color, die) -> {
			die.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.valueOf(color.name()), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
			die.setPadding(new Insets(15));
			die.setPrefSize(50, 50);
		});
		setLeft(dicePools);
		dicePools.setSpacing(10);
		dicePools.setAlignment(Pos.CENTER);
		setMargin(dicePools, new Insets(10));
		setCenter(scoreCards);
		scoreCards.setAlignment(Pos.CENTER);
		scoreCards.setSpacing(10);
		for (Color color : Color.values()) {
			getRowByColor(color).setSpacing(10);
			getRowByColor(color).setAlignment(Pos.CENTER);
		}
	}

	HBox getScoreRow() {
		return scoreRow;
	}

	VBox getScoreCards() {
		return scoreCards;
	}

	HashMap<Color, HBox> getRowByColorMap() {
		return rowByColorMap;
	}

	HBox getRowByColor(Color color) {
		return rowByColorMap.get(color);
	}

	VBox getDicePools() {
		return dicePools;
	}

	HashMap<Color, Label> getDieByColorMap() {
		return dieByColorMap;
	}

	Label getDieByColor(Color color) {
		return dieByColorMap.get(color);
	}

	Label[] getPublicDice() {
		return publicDice;
	}

	Button getRollDiceButton() {
		return rollDiceButton;
	}
}
