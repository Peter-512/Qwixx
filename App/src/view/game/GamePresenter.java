package App.src.view.game;

import App.src.model.Game;

public class GamePresenter {
	Game model;
	GameView view;

	public GamePresenter(Game model, GameView view) {
		this.model = model;
		this.view = view;
		
		addEventHandlers();
		updateView();
	}

	private void addEventHandlers() {
	}

	private void updateView() {
	}


}
