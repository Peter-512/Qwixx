package App.src.view.statistics;

import App.src.model.Game;
import App.src.view.mainMenu.MainMenuPresenter;
import App.src.view.mainMenu.MainMenuView;

import java.sql.*;

public class StatisticsPresenter {
    private Game model;
    private StatisticsView view;


    public StatisticsPresenter(Game model, StatisticsView view){
        this.model = model;
        this.view = view;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {view.getBackButton().setOnAction(actionEvent -> backToMainMenu());}
    private void updateView() {
    }


    private void backToMainMenu(){
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(model,mainMenuView);
        view.getScene().setRoot(mainMenuView);
    }
}