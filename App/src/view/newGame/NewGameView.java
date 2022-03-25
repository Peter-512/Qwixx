package App.src.view.newGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NewGameView extends BorderPane {
	private TextField nameTextField;
	private Label nameLabel;
	private Button startButton;
	private HBox hBox;
	private Button back;

	public NewGameView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		nameTextField = new TextField();
		nameLabel = new Label("Name");
		startButton = new Button("Start");
		hBox = new HBox(nameLabel, nameTextField, startButton);
		back = new Button("Back");
	}

	private void layoutNodes() {
		setCenter(hBox);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
		setBottom(back);
		setAlignment(back, Pos.CENTER);
		setMargin(back, new Insets(10));

	}

	Button getBack() {
		return back;
	}

	TextField getNameTextField() {
		return nameTextField;
	}

	Label getNameLabel() {
		return nameLabel;
	}

	Button getStartButton() {
		return startButton;
	}
}
