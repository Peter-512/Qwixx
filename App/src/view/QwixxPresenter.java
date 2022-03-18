package App.src.view;

import App.src.model.Game;
import javafx.scene.control.Button;

public class QwixxPresenter {
	private Game model;
	private QwixxView view;

	public QwixxPresenter(Game model, QwixxView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {

	}

	private void updateView() {
		Button[][] numberFields = view.getNumberFields();
		for (int i = 0; i < numberFields.length; i++) {
			for (int j = 0; j < numberFields[i].length; j++) {
				//				numberFields[i][j].setText(Integer.toString(model.getGameSession()
				//				                                                 .getPlayerSession()
				//				                                                 .getScoreCard()
				//				                                                 .getRow(Color.values()[i])
				//				                                                 .getNumberField(j + 2)
				//				                                                 .getValue()));
				numberFields[i][j].setText("fuck" + i + j);
			}
		}
	}


}
