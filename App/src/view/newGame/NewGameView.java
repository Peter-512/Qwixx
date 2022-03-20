package App.src.view.newGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class NewGameView extends BorderPane {
	private Button back;

	public NewGameView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		back = new Button("Back");

	}

	private void layoutNodes() {
		setBottom(back);
		setAlignment(back, Pos.CENTER);
		setMargin(back, new Insets(10));

	}

	Button getBack() {
		return back;
	}
}
