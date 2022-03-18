package App.src.view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class QwixxView extends BorderPane {
	private static final int CONTROL_MIN_SIZE = 35;
	private static final int PADDING = 10;
	private GridPane scoreCard;
	private Button[][] numberFields = new Button[4][11];


	public QwixxView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		scoreCard = new GridPane();

		for (int i = 0; i < numberFields.length; i++) {
			for (int j = 0; j < numberFields[i].length; j++) {
				scoreCard.add(new Button("help"), j, i);
			}
		}
	}

	private void layoutNodes() {
		setCenter(scoreCard);
	}

	GridPane getScoreCard() {
		return scoreCard;
	}

	Button[][] getNumberFields() {
		return numberFields;
	}
}
