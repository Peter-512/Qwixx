package src.view.newGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewGameView extends BorderPane {
	private TextField nameTextField;
	private Label nameLabel;
	private Button startButton;
	HBox hBox;
	HBox options;
	private VBox vbox;
	private Button backButton;
	private CheckBox startingPlayer;
	private CheckBox hardMode;

	public NewGameView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		nameTextField = new TextField();
		nameLabel = new Label("Name");
		startButton = new Button("Start");
		startingPlayer = new CheckBox("Do you want to be the starting player?");
		hardMode = new CheckBox("Hard Mode");
		hBox = new HBox(nameLabel, nameTextField, startButton);
		options = new HBox(startingPlayer, hardMode);
		vbox = new VBox(hBox, options);
		backButton = new Button("Back");
	}

	private void layoutNodes() {

		setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
		options.setAlignment(Pos.CENTER);
		options.setSpacing(20);
		setBottom(backButton);
		setAlignment(backButton, Pos.CENTER);
		setMargin(backButton, new Insets(10));
		startButton.setDefaultButton(true);
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

	CheckBox getStartingPlayer() {
		return startingPlayer;
	}

	CheckBox getHardMode() {
		return hardMode;
	}

}
