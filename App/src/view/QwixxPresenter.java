package App.src.view;
import App.src.Main;
import App.src.view.QwixxPresenter;
import App.src.model.Game;
import App.src.view.QwixxView;

import javafx.scene.control.Button;

public class QwixxPresenter extends Main {
	private Game model;
	private QwixxView view;


	public QwixxPresenter(Game model, QwixxView view) {
		this.model = model;
		this.view = view;

		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {
		view.getNewGame().setOnAction(e -> setGameView()) ;


	}

	private void setGameView() {
		QwixxView gameView = new QwixxView();
		QwixxPresenter gamePresenter = new QwixxPresenter(model, gameView);
		view.getScene().setRoot(gameView);
		gameView.getScene().getWindow().sizeToScene();

	}

	private void updateView() {

	}


}
