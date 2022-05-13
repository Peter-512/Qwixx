package src.view.endScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndScreenView extends BorderPane {
	Text text;
	Button button;

	public EndScreenView(boolean playerWon) {
		initializeNodes(playerWon);
		layoutNodes();
	}

	private void initializeNodes(boolean playerWon) {
		text = playerWon ? new Text("You won!") : new Text("You lost!");
		text.setFont(new Font(70));

		button = new Button("Return to main menu");
	}

	private void layoutNodes() {
		setCenter(text);
		setBottom(button);
		setAlignment(button, Pos.CENTER);
		setMargin(button, new Insets(10));
	}

	Button getButton() {
		return button;
	}
}
