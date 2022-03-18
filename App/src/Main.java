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


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, Color.BLANCHEDALMOND);
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setResizable(false);
		stage.setTitle("Qwixx");
		scene.setCursor(Cursor.HAND);

		////      Main large frame
		//        Rectangle rectangle_main = new Rectangle();
		//        rectangle_main.setWidth(1183);
		//        rectangle_main.setHeight(760);
		//        rectangle_main.setX(0);
		//        rectangle_main.setY(0);
		//        rectangle_main.setFill(Color.BLANCHEDALMOND);
		//        rectangle_main.setStrokeWidth(10);
		//        rectangle_main.setStroke(Color.BLACK);
		//
		////    Smaller rectangle who has nav
		//        Rectangle rectangle = new Rectangle();
		//        rectangle.setX(420);
		//        rectangle.setY(200);
		//        rectangle.setWidth(300);
		//        rectangle.setHeight(400);
		//        rectangle.setFill(Color.BLANCHEDALMOND);
		//        rectangle.setStrokeWidth(3);
		//        rectangle.setStroke(Color.BLACK);
		//
		//
		////    text
		//      Text qwixx = new Text();
		//        qwixx.setX(430);
		//        qwixx.setY(150);
		//        qwixx.setText("Qwixx");
		//        qwixx.setFont(Font.font("Montserrat",100));
		//
		//
		//// To do: replacement of the buttons and link them to the other scene
		//      Button nav = new Button("New Game");
		////      nav.setOnAction(e -> stage.setScene());
		//        nav.setStyle("-fx-font: 22 arial;");
		//
		//
		//     //nodes
		//        root.getChildren().add(rectangle_main);
		//        root.getChildren().add(rectangle);
		//        root.getChildren().add(qwixx);
		//        root.getChildren().add(nav);

		QwixxView view = new QwixxView();
		Game model = new Game();

		new QwixxPresenter(model, view);
		stage.setScene(new Scene(view));
		stage.show();

	}
}
