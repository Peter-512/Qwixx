package App.src.view.mainMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenuView extends BorderPane {
	private VBox vBox;
	private Button newGame;
	private Button statistics;
	private Button rules;
	private Text qwixx;


	public MainMenuView() {
		initializeNodes();
		layoutNodes();
	}


	private void initializeNodes() {
		qwixx = new Text("Qwixx");
		qwixx.setFont(new Font(70));
		newGame = new Button("New Game");
		newGame.setFont(new Font(20));
		statistics = new Button("Statistics");
		statistics.setFont(new Font(20));
		rules = new Button("Rules");
		rules.setFont(new Font(20));
		vBox = new VBox(qwixx, newGame, statistics, rules);
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

	Text getQwixx() {return qwixx;}

}
