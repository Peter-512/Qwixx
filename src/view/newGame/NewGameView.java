package src.view.newGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;

public class NewGameView extends BorderPane {
	private TextField nameTextField;
	private Label nameLabel;
	private Button startButton;
	private HBox hBox;
	private Button backButton;
	private CheckBox startingPlayer;

	public NewGameView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		nameTextField = new TextField();
		nameLabel = new Label("Name");
		startButton = new Button("Start");
		startingPlayer = new CheckBox("Do you want to start?");
		hBox = new HBox(nameLabel, nameTextField, startButton, startingPlayer);
		backButton = new Button("Back");
	}

	private void layoutNodes() {

		setCenter(hBox);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
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

}
