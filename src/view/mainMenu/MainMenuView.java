package src.view.mainMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenuView extends BorderPane {
	private VBox vBox;
	private Button newGameButton;
	private Button rulesButton;

	public MainMenuView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		Text titleText = new Text("Qwixx");
		titleText.setFont(new Font(70));
		newGameButton = new Button("New Game");
		newGameButton.setDefaultButton(true);
		newGameButton.setFont(new Font(20));
		rulesButton = new Button("Rules");
		rulesButton.setFont(new Font(20));
		vBox = new VBox(titleText, newGameButton, rulesButton);
	}

	private void layoutNodes() {
		setCenter(vBox);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
	}

	Button getNewGameButton() {
		return newGameButton;
	}

	Button getRulesButton() {
		return rulesButton;
	}
}
