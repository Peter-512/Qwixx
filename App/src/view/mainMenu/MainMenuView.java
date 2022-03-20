package App.src.view.mainMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainMenuView extends BorderPane {
	private VBox vBox;
	private Button newGame;
	private Button statistics;
	private Button rules;


	public MainMenuView() {
		initializeNodes();
		layoutNodes();
	}


	private void initializeNodes() {
		newGame = new Button("New Game");
		newGame.setFont(new Font(20));
		statistics = new Button("Statistics");
		statistics.setFont(new Font(20));
		rules = new Button("Rules");
		rules.setFont(new Font(20));
		vBox = new VBox(newGame, statistics, rules);
	}

	private void layoutNodes() {
		setCenter(vBox);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
	}

	Button getNewGame() {
		return newGame;
	}

	Button getStatistics() {
		return statistics;
	}

	Button getRules() {
		return rules;
	}
}
