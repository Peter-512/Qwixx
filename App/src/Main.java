package App.src;

import App.src.model.Game;
import App.src.view.QwixxPresenter;
import App.src.view.QwixxView;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Qwixx");
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setResizable(false);

		QwixxView view = new QwixxView();
		Game model = new Game();
		QwixxPresenter presenter = new QwixxPresenter(model, view);
		stage.setScene(new Scene(new QwixxView()));
		stage.show();


//		Group root = new Group();
//		Scene scene = new Scene(root, Color.BLANCHEDALMOND);
//		stage.setWidth(1200);
//		stage.setHeight(800);
//		stage.setResizable(false);
//		stage.setTitle("Qwixx");
//		scene.setCursor(Cursor.HAND);









	}
}
