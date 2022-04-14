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
	private VBox dicePools;
	private HashMap<Color, Label> dieByColorMap = new HashMap<>();
	private Label[] publicDice = new Label[2];
	private Button rollDiceButton;
	private Button penaltyButton;
	private Button passButton;
	private Label timeLabel;
	private Label currentPlayer;


	public GameView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {

		//		Setting up time
		timeLabel = new Label("00:00");

		//		Setting up scoreCard
		scoreCards = new VBox();
		for (int i = 0; i < 2; i++) {
			scoreCards.getChildren().add(new ScoreCardView());
		}

		//		Setting up dice
		dicePools = new VBox();
		for (Color color : Color.values()) {
			Label die = new Label();
			dieByColorMap.put(color, die);
			dicePools.getChildren().add(die);
		}
		for (int i = 0; i < publicDice.length; i++) {
			publicDice[i] = new Label();
			dicePools.getChildren().add(publicDice[i]);
		}

		currentPlayer = new Label();
		passButton = new Button("Pass");
		penaltyButton = new Button("Take penalty");
		rollDiceButton = new Button("Roll dice");
		dicePools.getChildren().addAll(rollDiceButton, passButton, penaltyButton, currentPlayer);


	}

	private void layoutNodes() {

		//		Laying out public dice
		for (Label die : publicDice) {
			die.setPrefSize(50, 50);
			die.setPadding(new Insets(15));
			die.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
		}

		//		Laying out colored dice
		dieByColorMap.forEach((color, die) -> {
			die.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.valueOf(color.name()), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
			die.setPadding(new Insets(15));
			die.setPrefSize(50, 50);
		});

		setRight(timeLabel);
		setMargin(timeLabel, new Insets(25));
		timeLabel.setPrefWidth(60);

		setLeft(dicePools);
		dicePools.setSpacing(10);
		dicePools.setAlignment(Pos.CENTER);
		setMargin(dicePools, new Insets(10));

		setCenter(scoreCards);
		scoreCards.setAlignment(Pos.CENTER);
		scoreCards.setSpacing(50);
	}

	VBox getScoreCards() {
		return scoreCards;
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

	Label getTimeLabel() {
		return timeLabel;
	}

	Button getPenaltyButton() {
		return penaltyButton;
	}

	Label getCurrentPlayer() {
		return currentPlayer;
	}

	Button getPassButton() {
		return passButton;
	}

}
