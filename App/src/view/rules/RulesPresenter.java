package App.src.view.rules;

import App.src.model.Game;

public class RulesPresenter {
	private Game model;
	private RulesView view;

	public RulesPresenter(Game model, RulesView view) {
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
