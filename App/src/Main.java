package App.src;

import App.src.model.Game;
import App.src.view.mainMenu.MainMenuPresenter;
import App.src.view.mainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) {
		stage.setTitle("Qwixx");
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setResizable(false);

		MainMenuView view = new MainMenuView();
		Game model = new Game();
		new MainMenuPresenter(model, view);

		Scene scene = new Scene(view);
		scene.setCursor(Cursor.HAND);

		stage.setScene(scene);
		stage.show();
	}
}
