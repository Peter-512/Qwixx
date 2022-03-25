package App.src.view.statistics;

import App.src.model.Game;
import App.src.view.mainMenu.MainMenuView;

public class StatisticsPresenter {
    private Game model;
    private StatisticsView view;

    public StatisticsPresenter(Game model, StatisticsView view){
        this.model = model;
        this.view = view;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {}
    private void updateView() {}
}
