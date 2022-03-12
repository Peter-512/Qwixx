package App.src.UI;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {

    }


    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Stage stage1 = new Stage();
        Scene scene = new Scene(root, Color.BLUEVIOLET);
        stage1.setWidth(500);
        stage1.setHeight(600);
        stage1.setMaxWidth(700);
//        stage1.setResizable(false);
        stage1.setTitle("This is stage 1");
        scene.setCursor(Cursor.CLOSED_HAND);
        stage1.setScene(scene);
        stage1.show();
    }
}
