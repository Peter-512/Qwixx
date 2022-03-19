package App.src.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class QwixxView extends BorderPane {
	private QwixxView view;
	private Button newGame;
	private Button LeaderBoard;
	private Button rules;



	public QwixxView() {
		initializeNodes();
		layoutNodes();


	}

	private void addEventHandlers(){
//		newGame.setOnAction(event ->{
//			Text newGame = new Text();
//			newGame.setText("New Game");
//		});
	}



	private void initializeNodes() {


		this.newGame = new Button("New Game");
		newGame.setFont(new Font(20));
		this.LeaderBoard = new Button("LeaderBoard");
		LeaderBoard.setFont(new Font(20));
		this.rules = new Button("Rules");
		LeaderBoard.setFont(new Font(20));

	}

	private void layoutNodes() {
       this.setTop(this.newGame);
	   this.setCenter(this.LeaderBoard);
	   this.setBottom(this.rules);

//		BorderPane.setMargin(this.newGame, new Insets(10));
//		BorderPane.setMargin(this.LeaderBoard, new Insets(10));
//		BorderPane.setMargin(this.rules, new Insets(10));
//		BorderPane.setAlignment(this.LeaderBoard, Pos.CENTER_LEFT);


//		setCenter(newGame);
//		setLeft(LeaderBoard);


	}

	Button getNewGame(){
		return newGame;
	}


}
