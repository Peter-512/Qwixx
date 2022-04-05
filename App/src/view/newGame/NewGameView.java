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
	private Button backButton;

	public NewGameView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		nameTextField = new TextField();
		nameLabel = new Label("Name");
		startButton = new Button("Start");
		hBox = new HBox(nameLabel, nameTextField, startButton);
		backButton = new Button("Back");
	}

	private void layoutNodes() {
		setCenter(hBox);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
		setBottom(backButton);
		setAlignment(backButton, Pos.CENTER);
		setMargin(backButton, new Insets(10));

	}

	Button getBackButton() {
		return backButton;
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
